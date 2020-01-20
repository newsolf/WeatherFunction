package com.newolf.weatherfunction.model.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.app.base.BaseViewModel
import com.newolf.weatherfunction.app.executeResponse
import com.newolf.weatherfunction.model.SevenDay
import com.newolf.weatherfunction.model.repository.HomeRepository

class HomeViewModel : BaseViewModel() {
    private val repository by lazy { HomeRepository() }
    val mSevenDayBean: MutableLiveData<SevenDay> = MutableLiveData()

    fun getWeatherForecast(cityCode: String) {
        launch {
            val result =  repository.getWeatherForecast(cityCode)
            executeResponse(result, { mSevenDayBean.value = result.data }, { LogUtils.e("error = ${result.rcode} , ${result.rdesc}") })
        }

    }
}
