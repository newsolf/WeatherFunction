package com.newolf.weatherfunction.city

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
data class CityInfo(
    val info: List<Info>,
    val rcode: Int,
    val rdesc: String
)

data class Info(
    val citycode: String,
    val cityname: String
)