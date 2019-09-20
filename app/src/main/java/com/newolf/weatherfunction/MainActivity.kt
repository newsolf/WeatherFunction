package com.newolf.weatherfunction

import androidx.appcompat.app.ActionBarDrawerToggle
import com.blankj.utilcode.util.ToastUtils
import com.newolf.weatherfunction.app.base.BaseActivity
import com.newolf.weatherfunction.app.utils.ResUtils
import com.newolf.weatherfunction.home.DrawerMenuFragment
import com.newolf.weatherfunction.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar_main.*
import kotlin.properties.Delegates


class MainActivity : BaseActivity() {
    private var lastTime by Delegates.notNull<Long>()
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
        beginTransaction.replace(R.id.flContainer, HomeFragment.newInstance())
        beginTransaction.commitNowAllowingStateLoss()


    }

    override fun initListener() {

    }


    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTime < 1000) {
            super.onBackPressed()
        } else {
            lastTime = System.currentTimeMillis()
            ToastUtils.setMsgColor(ResUtils.getColor(R.color.colorAccent))
            ToastUtils.showShort(R.string.app_exit)
        }

    }


}
