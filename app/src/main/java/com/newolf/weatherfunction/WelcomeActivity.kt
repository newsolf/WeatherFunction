package com.newolf.weatherfunction

import android.os.CountDownTimer
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.newolf.weatherfunction.app.Navigate
import com.newolf.weatherfunction.app.base.BaseActivity
import com.newolf.weatherfunction.app.helper.DialogHelper
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity() {


    val millisInFuture: Long = 6000
    val countDownInterval: Long = 1000
    lateinit var timer: CountDownTimer

    override fun bindLayout(): Int {
        return R.layout.activity_welcome
    }

    override fun initView() {

    }

    override fun loadData() {
        timer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
                nextToHome()
            }

            override fun onTick(millisUntilFinished: Long) {
                val l = millisUntilFinished / countDownInterval
                tvJump?.text = getString(R.string.jump, l)
                if (l == 1.toLong()) {
                    request()
                }
            }

        }.start()


    }

    override fun initListener() {
        tvJump?.setOnClickListener {
            request()
        }
    }

    private fun request() {
        PermissionUtils.permission(PermissionConstants.LOCATION, PermissionConstants.STORAGE)
            .rationale { shouldRequest -> DialogHelper.showRationaleDialog(shouldRequest) }
            .rationale { shouldRequest -> ToastUtils.showLong(shouldRequest.toString()) }
            .callback(object : PermissionUtils.FullCallback{
                override fun onGranted(permissionsGranted: MutableList<String>?) {
                    LogUtils.e("permissionsGranted = $permissionsGranted")
                    nextToHome()
                }

                override fun onDenied(
                    permissionsDeniedForever: MutableList<String>?,
                    permissionsDenied: MutableList<String>?
                ) {
                    if (permissionsDeniedForever?.size == 0) {
                        DialogHelper.showOpenAppSettingDialog()
//                        ToastUtils.showShort("没有获得权限")
                    }
                    LogUtils.d(permissionsDeniedForever, permissionsDenied)
                }
            }).request()
    }


    private fun nextToHome() {
        Navigate.startMainActivity(this)
        finish()
    }

    override fun finish() {
        super.finish()
        timer.cancel()
    }
}
