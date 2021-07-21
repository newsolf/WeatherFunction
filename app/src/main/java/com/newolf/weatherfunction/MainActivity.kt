package com.newolf.weatherfunction

import android.text.TextUtils
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.newolf.weatherfunction.app.application.App
import com.newolf.weatherfunction.app.base.BaseVMActivity
import com.newolf.weatherfunction.app.utils.ResUtils
import com.newolf.weatherfunction.app.utils.WeatherResUtils
import com.newolf.weatherfunction.home.DrawerMenuFragment
import com.newolf.weatherfunction.home.HomeFragment
import com.newolf.weatherfunction.model.DetailBean
import com.newolf.weatherfunction.model.viewmodel.TodayWeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar_main.*


class MainActivity : BaseVMActivity<TodayWeatherViewModel>() {
    override fun providerVMClass(): Class<TodayWeatherViewModel>? = TodayWeatherViewModel::class.java
    private var lastTime: Long = 0
    private lateinit var homeFragment:HomeFragment
    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

        setSupportActionBar(toolBar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolBar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        var beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.flContainerDrawerMenu, DrawerMenuFragment.newInstance())
        beginTransaction.commitNowAllowingStateLoss()

        beginTransaction = supportFragmentManager.beginTransaction()
        homeFragment = HomeFragment.newInstance()
        beginTransaction.replace(R.id.flContainer, homeFragment)
        beginTransaction.commitNowAllowingStateLoss()
        if (TextUtils.isEmpty(App.cityCode)) {
            App.cityCode = "101010100"
            LogUtils.i("set cityCode = 101010100")
        }
        updateTodayWeather(App.cityCode)
        homeFragment.updateCity(App.cityCode)


    }

    private fun updateTodayWeather(cityCode: String) {
        LogUtils.e("cityCode = $cityCode")
        mViewModel.getTodayWeather(cityCode)
    }

    override fun startObserve() {
        mViewModel.run {
            mTodayWeatherBean.observe(this@MainActivity, Observer {
                it.run {
                    LogUtils.e("updatetime = ${it.updatetime}")
                    updateTodayWeatherUi(it)

                }
            })
        }
    }

    private fun updateTodayWeatherUi(detailBean: DetailBean) {
        refreshLayout.finishRefresh()
        tvWeather.text = detailBean.phenomena
        ivWeatherIcon.setImageResource(WeatherResUtils.getWeatherRes(detailBean.phenomena))
        tvTemp.text = detailBean.temperature
        tvPublishTime.text = detailBean.updatetime
        homeFragment.updateTodayDetail(detailBean)
    }

    override fun initListener() {
        refreshLayout.setOnRefreshListener{
            updateTodayWeather(App.cityCode)
            homeFragment.updateCity(App.cityCode)
        }


    }


    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTime < 2000) {
            super.onBackPressed()
        } else {
            ToastUtils.make().setBgColor(ResUtils.getColor(R.color.colorAccent))
            ToastUtils.showShort(R.string.app_exit)
        }
        lastTime = System.currentTimeMillis();
    }

    fun updateCityName(cityName: String) {
        LogUtils.e("updateCityName cityName = $cityName")
        supportActionBar?.title = cityName
    }

}
