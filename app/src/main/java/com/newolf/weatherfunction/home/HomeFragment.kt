package com.newolf.weatherfunction.home

import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.MainActivity
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.base.BaseVMFragment
import com.newolf.weatherfunction.app.bean.LifeDetail
import com.newolf.weatherfunction.app.bean.TodayDetail
import com.newolf.weatherfunction.model.DetailBean
import com.newolf.weatherfunction.model.Forecast
import com.newolf.weatherfunction.model.SevenDay
import com.newolf.weatherfunction.model.Suggestion
import com.newolf.weatherfunction.model.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseVMFragment<HomeViewModel>() {
    override fun providerVMClass(): Class<HomeViewModel>? = HomeViewModel::class.java
    companion object {
        fun newInstance() = HomeFragment()
    }

    private  var mCityCode:String = ""
    override fun bindLayout(): Int {
        return R.layout.home_fragment
    }

    private lateinit var detailAdapter :DetailAdapter
    private lateinit var forecastAdapter :ForecastAdapter
    private lateinit var lifeIndexAdapter :LifeIndexAdapter

    override fun initView() {
        rvDetail.isNestedScrollingEnabled = false
        rvDetail.layoutManager = GridLayoutManager(mContext,3)
        detailAdapter =  DetailAdapter(null)
        rvDetail.adapter = detailAdapter

        rvForecast.layoutManager = LinearLayoutManager(mContext)
        forecastAdapter = ForecastAdapter(null)
        rvForecast.adapter = forecastAdapter


        rvLifeIndex.layoutManager = GridLayoutManager(mContext,4)
        lifeIndexAdapter =  LifeIndexAdapter(null)
        rvLifeIndex.adapter = lifeIndexAdapter

    }

    override fun loadData() {
        mViewModel.getWeatherForecast(mCityCode)
    }

    override fun initListener() {

    }

    fun updateCity(cityCode: String) {
        LogUtils.e("updateCity cityCode =  $cityCode")
        if(TextUtils.isEmpty(mCityCode)){
            mCityCode = cityCode
        }else{
            mCityCode = cityCode
            loadData()
        }
    }

    override fun startObserve() {
        mViewModel.run {
            mSevenDayBean.observe(this@HomeFragment, Observer {
                it?.run {
                    LogUtils.e("mSevenDayBean = $it")
                    updateSevenDayAndLifeUI(it)
                }
            })
        }
    }

    private fun updateSevenDayAndLifeUI(sevenDay: SevenDay) {
        updateCityName(sevenDay.cityname)
        updateSevenDayUI(sevenDay)
        updateLife(sevenDay.suggestion)
    }

    private fun updateCityName(cityname: String) {
        (activity as MainActivity).updateCityName(cityname)
    }

    private fun updateSevenDayUI(sevenDay: SevenDay) {
        updateTodayDetailElse(sevenDay.forecast[0])
        forecastAdapter.setNewData(sevenDay.forecast)
    }

    private fun updateTodayDetailElse(forecast: Forecast) {
        val detailList: MutableList<TodayDetail> = ArrayList()
        forecast.apply {
            detailList.add(
                TodayDetail(
                    R.mipmap.ic_launcher,
                    R.string.detail_key_uv,
                    this.uv
                )
            )
            detailList.add(
                TodayDetail(
                    R.mipmap.ic_launcher,
                    R.string.detail_key_pcpn,
                    this.pcpn
                )
            )
            detailList.add(
                TodayDetail(
                    R.mipmap.ic_launcher,
                    R.string.detail_key_pop,
                    this.pop
                )
            )
            detailList.add(
                TodayDetail(
                    R.mipmap.ic_launcher,
                    R.string.detail_key_vis,
                    this.vis
                )
            )
        }
        LogUtils.e("updateTodayDetailElse $detailList")
        detailAdapter.addData(detailList)
    }

    private fun updateLife(suggestion: Suggestion) {
        val lifeList: MutableList<LifeDetail> = ArrayList()
        suggestion.apply {
            lifeList.add(LifeDetail(
                R.mipmap.ic_launcher,
                R.string.life_key_air,
                this.air.brf,
                this.air.txt
            ))

            lifeList.add(LifeDetail(
                R.mipmap.ic_launcher,
                R.string.life_key_comf,
                this.comf.brf,
                this.comf.txt
            ))

            lifeList.add(LifeDetail(
                R.mipmap.ic_launcher,
                R.string.life_key_drs,
                this.drs.brf,
                this.drs.txt
            ))

            lifeList.add(LifeDetail(
                R.mipmap.ic_launcher,
                R.string.life_key_flu,
                this.flu.brf,
                this.flu.txt
            ))

            lifeList.add(LifeDetail(
                R.mipmap.ic_launcher,
                R.string.life_key_sport,
                this.sport.brf,
                this.sport.txt
            ))

            lifeList.add(LifeDetail(
                R.mipmap.ic_launcher,
                R.string.life_key_trav,
                this.trav.brf,
                this.trav.txt
            ))

            lifeList.add(LifeDetail(
                R.mipmap.ic_launcher,
                R.string.life_key_uv,
                this.uv.brf,
                this.uv.txt
            ))

            lifeList.add(LifeDetail(
                R.mipmap.ic_launcher,
                R.string.life_key_cw,
                this.cw.brf,
                this.cw.txt
            ))
        }
        LogUtils.e("lifeList = $lifeList")
        lifeIndexAdapter.setNewData(lifeList)
    }

    fun updateTodayDetail(detailBean: DetailBean) {
        val detailList: MutableList<TodayDetail> = ArrayList()
        detailBean.apply {
            detailList.add(
                TodayDetail(
                    R.mipmap.ic_launcher,
                    R.string.detail_key_feelst,
                    this.feelst + "°C"
                )
            )
            detailList.add(
                TodayDetail(
                    R.mipmap.ic_launcher,
                    R.string.detail_key_humidity,
                    this.humidity + "%"
                )
            )
        }

        detailAdapter.setNewData(detailList)

    }


}
