package com.newolf.weatherfunction

import android.text.TextUtils
import androidx.appcompat.app.ActionBarDrawerToggle
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.newolf.weatherfunction.app.application.App
import com.newolf.weatherfunction.app.base.BaseVMActivity
import com.newolf.weatherfunction.app.utils.ResUtils
import com.newolf.weatherfunction.home.DrawerMenuFragment
import com.newolf.weatherfunction.home.HomeFragment
import com.newolf.weatherfunction.model.viewmodel.TodayWeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar_main.*


class MainActivity : BaseVMActivity<TodayWeatherViewModel>() {
    override fun providerVMClass(): Class<TodayWeatherViewModel>? = TodayWeatherViewModel::class.java
    private var lastTime: Long = 0
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
        val homeFragment = HomeFragment.newInstance()
        beginTransaction.replace(R.id.flContainer, homeFragment)
        beginTransaction.commitNowAllowingStateLoss()
        if (TextUtils.isEmpty(App.cityCode)) {
            App.cityCode = "101010100"
        }
        updateTodayWeather(App.cityCode)
        homeFragment.updateCity(App.cityCode)


    }

    private fun updateTodayWeather(cityCode: String) {
        LogUtils.e("cityCode = $cityCode")
        mViewModel.getTodayWeather(cityCode)

        LogUtils.e("updateTodayWeather = ${mViewModel.mTodayWeatherBean.value}")
    }

    override fun initListener() {
    }


    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTime < 2000) {
            super.onBackPressed()
        } else {
            ToastUtils.setBgColor(ResUtils.getColor(R.color.colorAccent))
            ToastUtils.showShort(R.string.app_exit)
        }
        lastTime = System.currentTimeMillis();
    }

}
