package com.newolf.weatherfunction.home

import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.base.BaseFragment

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun bindLayout(): Int {
        return R.layout.home_fragment
    }

    override fun initView() {

    }

    override fun loadData() {

    }

    override fun initListener() {

    }

    fun updateCity(cityCode: String) {
        LogUtils.e("cityCode =  $cityCode")
    }


}
