package com.newolf.weatherfunction.app.base

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.umeng.message.PushAgent

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2018/1/12
 * 描述:
 * 历史:<br/>
 * ================================================
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {
    lateinit var mContext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBaseView(bindLayout())
        PushAgent.getInstance(application).onAppStart();
        mContext = this
        getExtras()
        initView()
        loadData()
        initListener()
    }

    abstract fun bindLayout(): Int

    protected open fun setBaseView(@LayoutRes layoutId: Int) {
        setContentView(layoutId)
    }

    override fun getExtras() {
        getExtras(intent)
    }

    protected fun getExtras(intent: Intent) {

    }

    override fun loadData() {

    }

    override fun onStart() {
        super.onStart()
        BarUtils.setNavBarImmersive(this)
    }


    override fun onResume() {
        super.onResume()
        BarUtils.setNavBarImmersive(this)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}