package com.newolf.weatherfunction.app.application

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.newolf.weatherfunction.BuildConfig
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta

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
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initUtils(this)
        initBugly(this)
    }

    private fun initUtils(app: App) {
        Utils.init(app)
        LogUtils.Builder().setLogSwitch(BuildConfig.DEBUG)
    }

    private fun initBugly(context: Application) {
        Bugly.init(context, BuildConfig.BUGLY_APP_ID, BuildConfig.DEBUG)
        Beta.autoInit = true
        Beta.autoDownloadOnWifi = true
    }
}