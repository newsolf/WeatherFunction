package com.newolf.weatherfunction.app.utils

import android.app.Application
import com.newolf.weatherfunction.BuildConfig
import com.newolf.weatherfunction.app.application.App
import com.newolf.weatherfunction.app.utils.state.IStatsUtils
import com.newolf.weatherfunction.app.utils.state.StatsConstant
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

/**
 * StatsUtils
 *
 * @author NeWolf
 * @since 2021-05-28
 */
object StatsUtils : IStatsUtils {
    private val map = mutableMapOf<String, Any>()
    override fun initStats(app: Application) {
        UMConfigure.init(
            app,
            BuildConfig.STATS_KEY,
            BuildConfig.CHANNEL,
            UMConfigure.DEVICE_TYPE_PHONE,
            BuildConfig.MESSAGE_SECRET
        )
        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }

    override fun obtainMap(): MutableMap<String, Any> {
        map.clear()
        return map
    }

    override fun onEvent(eventId: String, map: MutableMap<String, Any>) {
        map[StatsConstant.D_NAME] = App.name
        MobclickAgent.onEventObject(App.CONTEXT, eventId, map)
    }
}