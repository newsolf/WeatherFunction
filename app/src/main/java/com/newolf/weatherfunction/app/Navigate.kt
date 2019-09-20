package com.newolf.weatherfunction.app

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.net.Uri
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.MainActivity

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
object Navigate {
    private fun startActivity(context: Context, clz: Class<*>) {
        context.startActivity(Intent(context, clz))
    }

    fun startInnerH5(content: Context?, url: String) {
        LogUtils.e(url)
        content?.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).setFlags(
                FLAG_ACTIVITY_CLEAR_TASK
            )
        )
    }

    fun startMainActivity(content: Context) {
        content.startActivity(
            Intent(content, MainActivity::class.java).setFlags(
                FLAG_ACTIVITY_CLEAR_TASK
            )
        )
    }

}