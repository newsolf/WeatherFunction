package com.newolf.weatherfunction.home

import android.widget.Button
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.Navigate
import com.newolf.weatherfunction.app.base.BaseFragment
import kotlinx.android.synthetic.*

class DrawerMenuFragment : BaseFragment() {
    lateinit var btnAddCity: Button

    companion object {
        fun newInstance() = DrawerMenuFragment()
    }

    override fun bindLayout(): Int {
        return R.layout.drawer_menu_fragment
    }

    override fun initView() {
        btnAddCity =  mRootView.findViewById<Button>(R.id.add_city_btn)
    }

    override fun loadData() {

    }

    override fun initListener() {
        btnAddCity.setOnClickListener {
            Navigate.startHeFengActivity(mContext)
        }
    }


}
