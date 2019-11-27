package com.newolf.weatherfunction.app.api

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2019-09-26
 * 描述:
 * 历史:<br/>
 * ================================================
 */

data class BaseResponse<out T>(
    val rcode: Int,
    val rdesc: String,
    val data: T
)




//    open val citycode: Int = 1,
//    open val cityname: String = "",
////                        TodayDetail
//    val airpressure: String = "",
//    val feelst: String = "",
//    val humidity: String = "",
//    val phenomena: String = "",
//    val rain: String = "",
//    val temperature: String = "",
//    val updatetime: String = "",
//    val winddirect: String = "",
//    val windpower: String = "",
//    val windspeed: String = "",
////                        SevenDay
//    val forecast: List<Forecast>,
//    val suggestion: Suggestion,
////                         AirLiveBean
//    val AQI: String = "",
//    val CO: String = "",
//    val NO2: String = "",
//    val PM10: String = "",
//    val PM25: String = "",
//    val SO2: String = "",
//    val o3: String = "",
//    val primary: String = "",
//    val time: String = ""


//)