package com.newolf.weatherfunction.model.viewmodel

import androidx.lifecycle.MutableLiveData
import com.newolf.weatherfunction.app.base.BaseViewModel
import com.newolf.weatherfunction.model.CityCodeBean
import com.newolf.weatherfunction.model.repository.TodayWeatherRepository

class TodayWeatherViewModel : BaseViewModel() {
    private val repository by lazy { TodayWeatherRepository() }
    val mTodayWeatherBean: MutableLiveData<CityCodeBean> = MutableLiveData()

    fun getTodayWeather(cityCode: String) {
        launch {
            val result = repository.getWeatherLive(cityCode)
//                mTodayWeatherBean.value = result.data

        }
    }
}
