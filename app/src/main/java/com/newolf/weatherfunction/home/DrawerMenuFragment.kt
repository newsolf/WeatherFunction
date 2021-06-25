package com.newolf.weatherfunction.home

import android.widget.Button
import com.ewolf.wolfanim.RotateImageView
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.Navigate
import com.newolf.weatherfunction.app.application.App
import com.newolf.weatherfunction.app.base.BaseFragment
import kotlinx.android.synthetic.*

class DrawerMenuFragment : BaseFragment() {
    private lateinit var btnAddCity: Button
    private lateinit var mRivAnim: RotateImageView

    companion object {
        fun newInstance() = DrawerMenuFragment()
    }

    override fun bindLayout(): Int {
        return R.layout.drawer_menu_fragment
    }

    override fun initView() {
        btnAddCity =  mRootView.findViewById<Button>(R.id.add_city_btn)
        mRivAnim = mRootView.findViewById<RotateImageView>(R.id.riv_anim)
        mRivAnim.addLifecycleObserver(this)
    }

    override fun loadData() {

    }

    override fun initListener() {
        btnAddCity.setOnClickListener {
            Navigate.startHeFengActivity(mContext)
        }

        mRivAnim.setOnClickListener{
            App.isH5MainActivity = true
            Navigate.startMainActivity(mContext)
            activity?.finish()
        }
    }


}
