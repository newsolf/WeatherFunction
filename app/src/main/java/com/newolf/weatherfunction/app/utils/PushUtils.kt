package com.newolf.weatherfunction.app.utils

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.BuildConfig
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.PushAgent

/**
 * PushUtils$
 *
 * @author NeWolf
 * @since 2021-05-28
 */
object PushUtils {
    fun initPush(app: Application) {
        //预初始化
        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        UMConfigure.preInit(app,BuildConfig.STATS_KEY,BuildConfig.MESSAGE_SECRET)
        //正式初始化
        initPushSDK(app);
    }

    private fun initPushSDK(app: Application) {
        LogUtils.e(UMConfigure.isInit)
        UMConfigure.init(
            app,
            BuildConfig.STATS_KEY,
            BuildConfig.CHANNEL,
            UMConfigure.DEVICE_TYPE_PHONE,
            BuildConfig.MESSAGE_SECRET
        )
    }
}