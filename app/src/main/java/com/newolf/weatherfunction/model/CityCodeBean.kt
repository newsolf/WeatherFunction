package com.newolf.weatherfunction.model

import com.newolf.weatherfunction.app.api.BaseResponse


/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2019-09-27
 * 描述:
 * 历史:<br/>
 * ================================================
 */
 data class CityCodeBean (val citycode: String, val cityname: String, override val rcode: Int, override val rdesc: String):
    BaseResponse(rcode, rdesc)