package com.newolf.weatherfunction.app

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.net.Uri
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.MainActivity
import com.newolf.weatherfunction.app.application.App
import com.newolf.weatherfunction.app.base.InnerWebActivity
import com.newolf.weatherfunction.app.constant.Constants
import com.newolf.weatherfunction.black.BlackActivity
import com.newolf.weatherfunction.hefeng.HeFengActivity

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

    fun startInnerH5(context: Context, url: String) {
        LogUtils.e(url)
//        context?.startActivity(
//            Intent(Intent.ACTION_VIEW, Uri.parse(url)).setFlags(
//                FLAG_ACTIVITY_CLEAR_TASK
//            )
//        )

        val intent = Intent(context, InnerWebActivity::class.java)
        intent.putExtra(InnerWebActivity.STRING_EXTRA_URL, url)
        context.startActivity(intent)
    }

    fun startMainActivity(context: Context) {
        if (App.isH5MainActivity) {
            startInnerH5(context, Constants.HE_FENG_H5_URL)
        } else {
            context.startActivity(
                Intent(context, MainActivity::class.java).setFlags(
                    FLAG_ACTIVITY_CLEAR_TASK
                )
            )
        }
    }

    fun startHeFengActivity(context: Context) {
        context.startActivity(Intent(context, HeFengActivity::class.java))
    }

    fun startBlackActivity(context: Context) {
        context.startActivity(Intent(context, BlackActivity::class.java))
    }

}