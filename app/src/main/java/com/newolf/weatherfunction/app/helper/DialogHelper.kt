package com.newolf.weatherfunction.app.helper

import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.PermissionUtils
import com.newolf.weatherfunction.R

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2018/1/12
 * 描述:
 * 历史:<br/>
 * ================================================
 */
object DialogHelper {
    fun showRationaleDialog(shouldRequest: PermissionUtils.OnRationaleListener.ShouldRequest) {
        val topActivity = ActivityUtils.getTopActivity() ?: return
        AlertDialog.Builder(topActivity)
            .setTitle(android.R.string.dialog_alert_title)
            .setMessage(R.string.permission_rationale_message)
            .setPositiveButton(android.R.string.ok) { _, _ -> shouldRequest.again(true) }
            .setNegativeButton(android.R.string.cancel) { _, _ -> shouldRequest.again(false) }
            .setCancelable(false)
            .create()
            .show()

    }

    fun showOpenAppSettingDialog() {
        val topActivity = ActivityUtils.getTopActivity() ?: return
        AlertDialog.Builder(topActivity)
            .setTitle(android.R.string.dialog_alert_title)
            .setMessage(R.string.permission_denied_forever_message)
            .setPositiveButton(android.R.string.ok
            ) { _, _ -> PermissionUtils.launchAppDetailsSettings() }
            .setNegativeButton(android.R.string.cancel) { _, _ -> topActivity.finish()}
            .setCancelable(false)
            .create()
            .show()

    }


}