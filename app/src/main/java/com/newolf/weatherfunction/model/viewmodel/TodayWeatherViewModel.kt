package com.newolf.weatherfunction.model.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.app.base.BaseViewModel
import com.newolf.weatherfunction.app.executeResponse
import com.newolf.weatherfunction.model.DetailBean
import com.newolf.weatherfunction.model.repository.TodayWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodayWeatherViewModel : BaseViewModel() {
    private val repository by lazy { TodayWeatherRepository() }
    val mTodayWeatherBean: MutableLiveData<DetailBean> = MutableLiveData()

    fun getTodayWeather(cityCode: String) {
        launch {
            val result = repository.getWeatherLive(cityCode)
//                mTodayWeatherBean.value = result.data

        }

        launch {
            val result = withContext(Dispatchers.IO) { repository.getWeatherLive(cityCode) }
            executeResponse(result, { mTodayWeatherBean.value = result.data }, { LogUtils.e("error = ${result.rcode} , ${result.rdesc}") })
        }

    }
}
