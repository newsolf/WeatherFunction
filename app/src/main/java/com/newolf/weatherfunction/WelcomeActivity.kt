package com.newolf.weatherfunction

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.LocationManager
import android.os.CountDownTimer
import android.provider.Settings
import android.text.TextUtils
import androidx.lifecycle.Observer
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.*
import com.newolf.weatherfunction.app.Navigate
import com.newolf.weatherfunction.app.application.App
import com.newolf.weatherfunction.app.base.BaseVMActivity
import com.newolf.weatherfunction.app.constant.Constants
import com.newolf.weatherfunction.app.helper.DialogHelper
import com.newolf.weatherfunction.app.service.LocationService
import com.newolf.weatherfunction.app.utils.StatsUtils
import com.newolf.weatherfunction.app.utils.state.StatsConstant
import com.newolf.weatherfunction.model.viewmodel.CityCodeViewModel
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseVMActivity<CityCodeViewModel>() {
    override fun providerVMClass(): Class<CityCodeViewModel> = CityCodeViewModel::class.java

    val millisInFuture: Long = 6000
    val countDownInterval: Long = 1000
    lateinit var timer: CountDownTimer
    var currentCity: String = "当前"
    lateinit var mLocationService: LocationService
    lateinit var mLocationListener: BDAbstractLocationListener
    var hasAllPermission = false
    var cityCode: String? = ""


    override fun bindLayout(): Int {
        return R.layout.activity_welcome
    }

    override fun initView() {
        val app = application as App
        mLocationService = app.mLocationService
        request()
    }

    override fun loadData() {
        timer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
                tvJump.setText(R.string.app_enter)
                nextToHome()
            }

            override fun onTick(millisUntilFinished: Long) {
                val l = millisUntilFinished / countDownInterval
                tvJump?.text = getString(R.string.jump, l)
                if (l == 1.toLong()) {

                }
            }

        }.start()


    }

    override fun initListener() {
        tvJump?.setOnClickListener {
            nextToHome()
        }

    }


    private fun startLocation() {
        mLocationListener = object : BDAbstractLocationListener() {
            override fun onReceiveLocation(location: BDLocation?) {
                currentCity = location?.city.toString()

                if (TextUtils.isEmpty(currentCity)) {
                    ToastUtils.showShort("正在获取当前城市为，请等待...")
                    LogUtils.e("未获取到当前城市")
                    return
                }

                LogUtils.e("获取到当前城市为： $currentCity")
                val longitude = location?.longitude
                val latitude = location?.latitude
                val currentLocation = "${location?.locTypeDescription} ${location?.address?.address} ${location?.locationDescribe} loca:$longitude,$latitude"
                val currentLocationStats = "${location?.locTypeDescription} loca:$longitude,$latitude"
                LogUtils.e("currentLocation = $currentLocation")
                val obtainMap = StatsUtils.obtainMap()
                obtainMap[StatsConstant.CURRENT_LOCATION] = currentLocationStats
                StatsUtils.onEvent(StatsConstant.CURRENT_LOCATION, obtainMap)

                LogUtils.e("longitude = $longitude ,latitude =  $latitude")
                ToastUtils.showShort("获取到当前城市为： $currentCity")
                if (longitude != null && latitude != null && longitude + latitude != 0.toDouble()) {
                    stopLocation()
//                    requestCityCode(longitude, latitude)
                    requestCityCode(longitude, latitude)
                }
            }
        }

        if (!mLocationService.isStart) {
            mLocationService.registerListener(mLocationListener)
        }
        mLocationService.start()
    }

    private fun requestCityCode(longitude: Double, latitude: Double) {
        if (NetworkUtils.isConnected()) {
//            根据接口获取cityCode

            if (TextUtils.isEmpty(currentCity)) {
                ToastUtils.showShort("获取城市错误")
                return
            }

//            CityCodeRepository().getCityCode(longitude.toString(),latitude.toString())
            if (currentCity.endsWith("市")) {
                currentCity = currentCity.replace("市", "")
            }



            mViewModel.getCityCodeByCityName(currentCity)


        } else {
//            根据本地json获取
            LogUtils.e("no net ")
            cityCode = SPUtils.getInstance().getString(Constants.SP_STRING_CITY_CODE, "101010100")
            App.cityCode = cityCode.toString()

            currentCity = SPUtils.getInstance().getString(Constants.SP_STRING_CITY_NAME, "北京")
            App.cityName = currentCity


        }


    }

    override fun startObserve() {
        mViewModel.run {
            mCityCodeBean.observe(this@WelcomeActivity, Observer {
                it?.run {
                    LogUtils.e(mViewModel.mCityCodeBean.value?.citycode)
//                    101010100
                    cityCode = mViewModel.mCityCodeBean.value?.citycode
                    if (!TextUtils.isEmpty(cityCode)) {
                        App.cityCode = cityCode.toString()
                        SPUtils.getInstance().put(Constants.SP_STRING_CITY_CODE, cityCode.toString())
                        App.cityName = currentCity
                        SPUtils.getInstance().put(Constants.SP_STRING_CITY_NAME, currentCity)
                    }
                }
            })


        }
    }


    override fun onStop() {
        super.onStop()
        stopLocation()
    }

    private fun stopLocation() {
        if (mLocationService.isStart) {
            mLocationService.unregisterListener(mLocationListener)
        }
        mLocationService.stop()
    }

    private fun request() {
        PermissionUtils.permission(PermissionConstants.LOCATION, PermissionConstants.STORAGE)
                .rationale { shouldRequest -> DialogHelper.showRationaleDialog(shouldRequest) }
                .rationale { shouldRequest -> ToastUtils.showLong(shouldRequest.toString()) }
                .callback(object : PermissionUtils.FullCallback {
                    override fun onGranted(permissionsGranted: MutableList<String>?) {
                        LogUtils.e("permissionsGranted = $permissionsGranted")
//                    nextToHome()
                        hasAllPermission = true
                        checkGps()

                    }

                    override fun onDenied(
                            permissionsDeniedForever: MutableList<String>?,
                            permissionsDenied: MutableList<String>?
                    ) {
                        if (permissionsDeniedForever?.size == 0) {
                            DialogHelper.showOpenAppSettingDialog()
                            hasAllPermission = false
//                        ToastUtils.showShort("没有获得权限")
                        }
                        LogUtils.d(permissionsDeniedForever, permissionsDenied)
                    }
                }).request()
    }

    private fun checkGps() {
        val alm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            startLocation()
        } else {
            showNeedGpsDialog()
        }
    }

    private fun showNeedGpsDialog() {
        val alertBuild = AlertDialog.Builder(mContext)
        alertBuild.setTitle(getString(R.string.need_gps_title)).setMessage(getString(R.string.need_gps_msg)).setNegativeButton(getString(R.string.need_gps_negative)) { dialog, _ ->
            dialog.dismiss()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext.startActivity(intent)
        }
        alertBuild.create().show()
    }

    override fun onRestart() {
        super.onRestart()
        request()
    }


    private fun nextToHome() {
        if (!hasAllPermission) {
            DialogHelper.showOpenAppSettingDialog()
            return
        }

        if (TextUtils.equals("当前", currentCity)) {
            ToastUtils.showShort(R.string.please_waite_current_city)
            return
        }

//        if (TextUtils.isEmpty(cityCode)) {
//            ToastUtils.showShort(R.string.please_waite_current_code)
//            return
//        }


        Navigate.startMainActivity(this)
        finish()
    }

    override fun finish() {
        super.finish()
        timer.cancel()
    }
}
