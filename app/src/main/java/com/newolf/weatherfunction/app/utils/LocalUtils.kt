package com.newolf.weatherfunction.app.utils

import android.content.Context

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2020-02-03
 * 描述:
 * 历史:<br/>
 * ================================================
 */
object LocalUtils {
    fun init(context: Context) {
        val appContext = context.applicationContext
        ResUtils.init(appContext)
        WeatherResUtils.init(appContext)
    }
}