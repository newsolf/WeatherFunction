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


//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        ToastUtils.showShort("onKeyDown")
//        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
//            if (System.currentTimeMillis() - lastTime > 2000) {
//                ToastUtils.setMsgColor(ResUtils.getColor(R.color.colorAccent))
//                ToastUtils.showShort(R.string.app_exit)
//                lastTime = System.currentTimeMillis()
//            } else {
//                ActivityUtils.finishAllActivities()
//            }
//            return true
//        }
//        return super.onKeyDown(keyCode, event)
//    }


    override fun onBackPressed() {

        ToastUtils.showShort("onBackPressed")
        if (System.currentTimeMillis() - lastTime < 2000) {
            super.onBackPressed()
        } else {
            ToastUtils.setBgColor(ResUtils.getColor(R.color.colorAccent))
            ToastUtils.showShort(R.string.app_exit)
        }
        lastTime = System.currentTimeMillis();
        TODO("为啥不起作用呢？")
    }

}
