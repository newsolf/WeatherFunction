package com.newolf.weatherfunction.model.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.app.base.BaseViewModel
import com.newolf.weatherfunction.app.executeResponse
import com.newolf.weatherfunction.model.CityCodeBean
import com.newolf.weatherfunction.model.repository.CityCodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
class CityCodeViewModel : BaseViewModel(){
    private val repository by lazy { CityCodeRepository() }
    val mCityCodeBean: MutableLiveData<CityCodeBean> = MutableLiveData()

    fun getCityCodeByCityName(cityName: String) {
//        launch {
//            val result = repository.getCityCodeByCityName(cityName)
//            executeResponse(result, {
//                LogUtils.e(result.data)
//                mCityCodeBean.value = result.data
//            }, {LogUtils.e("error = ${result.rcode} , ${result.rdesc}")})
//        }

        launch {
            val result = withContext(Dispatchers.IO) { repository.getCityCodeByCityName(cityName) }
            executeResponse(result, { mCityCodeBean.value = result.data }, { LogUtils.e("error = ${result.rcode} , ${result.rdesc}") })
        }
    }
}