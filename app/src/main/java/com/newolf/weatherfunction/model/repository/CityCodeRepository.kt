package com.newolf.weatherfunction.model.repository

import com.newolf.weatherfunction.app.api.BaseRepository
import com.newolf.weatherfunction.app.api.BaseResponse
import com.newolf.weatherfunction.app.api.WeatherApiService

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
class CityCodeRepository :BaseRepository() {
    suspend fun getCityCode(lng :String,lat: String): BaseResponse {
        return apiCall { WeatherApiService.service.getCityCode(lng,lat) }
    }
    suspend fun getCityCodeByCityName(cityName :String): BaseResponse {
        return apiCall { WeatherApiService.service.getCityCodeByCityName(cityName) }
    }
}