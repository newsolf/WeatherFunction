package com.newolf.weatherfunction.app.utils.state

import android.app.Application

/**
 * IState
 *
 * @author NeWolf
 * @since 2021-05-28
 */
interface IStatsUtils {
    /**
     * 初始化
     *
     *@param app Application
     */
    fun initStats(app: Application)

    /**
     * 获取一个可以存数据的map
     *
     * @return MutableMap<String, Any>
     */
    fun obtainMap(): MutableMap<String, Any>

    /**
     * 自定义事件
     */
    fun onEvent(eventId: String, map: MutableMap<String, Any>)
}