package com.newolf.weatherfunction

import android.os.CountDownTimer
import android.text.TextUtils
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.newolf.weatherfunction.app.Navigate
import com.newolf.weatherfunction.app.application.App
import com.newolf.weatherfunction.app.base.BaseActivity
import com.newolf.weatherfunction.app.helper.DialogHelper
import com.newolf.weatherfunction.app.service.LocationService
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity {


    val millisInFuture: Long = 6000
    val countDownInterval: Long = 1000
    lateinit var timer: CountDownTimer
    var currentCity: String = "当前"
    lateinit var mLocationService: LocationService
    lateinit var mLocationListener: BDAbstractLocationListener
    var hasAllPermission = false
    var cityCode = ""

    constructor() : super()

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
                LogUtils.e("获取到当前城市为： $currentCity")
                val longitude = location?.longitude
                val latitude = location?.latitude
                LogUtils.e("longitude = $longitude ,latitude =  $latitude")
                ToastUtils.showShort("获取到当前城市为： $currentCity")
                if (longitude != null && latitude != null && longitude + latitude != 0.toDouble()) {
                    stopLocation()
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
        if (NetworkUtils.isConnected()){
//            根据接口获取cityCode
        }else {
//            根据本地json获取
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
                    startLocation()
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

    override fun onRestart() {
        super.onRestart()
        request()
    }


    private fun nextToHome() {
        if (!hasAllPermission){
            DialogHelper.showOpenAppSettingDialog()
            return
        }

        if(TextUtils.equals("当前",currentCity)){
            ToastUtils.showShort(R.string.please_waite_current_city)
            return
        }
        Navigate.startMainActivity(this)
        finish()
    }

    override fun finish() {
        super.finish()
        timer.cancel()
    }
}
