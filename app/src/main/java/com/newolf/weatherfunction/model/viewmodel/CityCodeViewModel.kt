package com.newolf.weatherfunction.model.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.app.api.BaseResponse
import com.newolf.weatherfunction.app.base.BaseViewModel
import com.newolf.weatherfunction.app.executeResponse
import com.newolf.weatherfunction.model.repository.CityCodeRepository

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
    val mCityCodeBean: MutableLiveData<BaseResponse> = MutableLiveData()

    fun getCityCodeByCityName(cityName: String) {
        launch {
            val result = repository.getCityCodeByCityName(cityName)
            LogUtils.e(result.rcode)
            executeResponse(result, {
                LogUtils.e(result.rcode,result.rdesc )
                mCityCodeBean.value = result
            }, {LogUtils.e("error")})
        }
    }
}