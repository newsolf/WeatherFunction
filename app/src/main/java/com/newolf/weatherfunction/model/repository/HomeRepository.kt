package com.newolf.weatherfunction.model.repository

import com.newolf.weatherfunction.app.api.BaseRepository
import com.newolf.weatherfunction.app.api.BaseResponse
import com.newolf.weatherfunction.app.api.WeatherApiService
import com.newolf.weatherfunction.model.SevenDay

class HomeRepository : BaseRepository() {
    suspend fun getWeatherForecast(cityCode : String): BaseResponse<SevenDay> {
        return apiCall { WeatherApiService.service.getWeatherForecast(cityCode) }
    }
}
