package com.newolf.weatherfunction.model.repository

import com.newolf.weatherfunction.app.api.BaseRepository
import com.newolf.weatherfunction.app.api.BaseResponse
import com.newolf.weatherfunction.app.api.WeatherApiService
import com.newolf.weatherfunction.model.DetailBean

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
class TodayWeatherRepository :BaseRepository() {

    suspend fun getWeatherLive(cityCode : String): BaseResponse<DetailBean> {
        return apiCall { WeatherApiService.service.getWeatherLive(cityCode) }
    }
}