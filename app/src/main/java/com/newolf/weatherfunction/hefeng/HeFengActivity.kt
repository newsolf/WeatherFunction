package com.newolf.weatherfunction.hefeng

import android.graphics.Color
import android.widget.Button
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.Navigate
import com.newolf.weatherfunction.app.base.BaseVMActivity
import com.newolf.weatherfunction.app.base.EmptyViewModel
import com.qweather.plugin.view.*


class HeFengActivity : BaseVMActivity<EmptyViewModel>() {
    override fun providerVMClass(): Class<EmptyViewModel> = EmptyViewModel::class.java
    override fun bindLayout(): Int {
        return R.layout.activity_he_feng
    }

    override fun initView() {
        //横向布局
        //横向布局
        val horizonView = findViewById<HorizonView>(R.id.horizon_view)
        //取消默认背景
        //取消默认背景
        horizonView.setDefaultBack(false)
        //设置布局的背景圆角角度，颜色，边框宽度，边框颜色
        //设置布局的背景圆角角度，颜色，边框宽度，边框颜色
        horizonView.setStroke(5, Color.BLUE, 1, Color.BLUE)
        //添加地址文字描述，第一个参数为文字大小，单位：sp ，第二个参数为文字颜色，默认白色
        //添加地址文字描述，第一个参数为文字大小，单位：sp ，第二个参数为文字颜色，默认白色
        horizonView.addLocation(14, Color.WHITE)
        //添加预警图标，参数为图标大小，单位：dp
        //添加预警图标，参数为图标大小，单位：dp
        horizonView.addAlarmIcon(14)
        //添加预警文字
        //添加预警文字
        horizonView.addAlarmTxt(14)
        //添加温度描述
        //添加温度描述
        horizonView.addTemp(14, Color.WHITE)
        //添加天气图标
        //添加天气图标
        horizonView.addWeatherIcon(14)
        //添加天气描述
        //添加天气描述
        horizonView.addCond(14, Color.WHITE)
        //添加风向图标
        //添加风向图标
        horizonView.addWindIcon(14)
        //添加风力描述
        //添加风力描述
        horizonView.addWind(14, Color.WHITE)
        //添加文字：AQI
        //添加文字：AQI
        horizonView.addAqiText(14, Color.WHITE)
        //添加空气质量描述
        //添加空气质量描述
        horizonView.addAqiQlty(14)
        //添加空气质量数值描述
        //添加空气质量数值描述
        horizonView.addAqiNum(14)
        //添加降雨图标
        //添加降雨图标
        horizonView.addRainIcon(14)
        //添加降雨描述
        //添加降雨描述
        horizonView.addRainDetail(14, Color.WHITE)
        //设置控件的对齐方式，默认居中
        //设置控件的对齐方式，默认居中
        horizonView.setViewGravity(HeContent.GRAVITY_CENTER)
        //设置控件的内边距，默认为0
        //设置控件的内边距，默认为0
        horizonView.setViewPadding(10, 10, 10, 10)
        //显示控件
        //显示控件
        horizonView.show()


        show1()
        show2()

    }

    /**
     * 显示右1左2横向布局
     */
    private fun show1() {
        val rlView = findViewById<RightLargeView>(R.id.rl_view)
        //方法参数同（7）左侧大布局右侧双布局
        //方法参数同（7）左侧大布局右侧双布局
        val rightLayout = rlView.rightLayout
        val leftTopLayout = rlView.leftTopLayout
        val leftBottomLayout = rlView.leftBottomLayout

        //取消默认背景

        //取消默认背景
        rlView.setDefaultBack(false)
        rlView.setStroke(0, Color.GRAY, 1, Color.WHITE)
        rlView.addLocation(leftTopLayout, 14, Color.WHITE)
        rlView.addAqiText(leftTopLayout, 14)
        rlView.addAqiQlty(leftTopLayout, 14)
        rlView.addAqiNum(leftTopLayout, 14)
        rlView.addAlarmIcon(leftTopLayout, 14)
        rlView.addAlarmTxt(leftTopLayout, 14)
        rlView.addWeatherIcon(leftTopLayout, 14)

        rlView.addRainIcon(leftBottomLayout, 14)
        rlView.addRainDetail(leftBottomLayout, 14, Color.WHITE)
        rlView.addWindIcon(leftBottomLayout, 14)
        rlView.addWind(leftBottomLayout, 14, Color.WHITE)
        rlView.addCond(leftBottomLayout, 14, Color.WHITE)

        rlView.addTemp(rightLayout, 40, Color.WHITE)
        rlView.setViewGravity(HeContent.GRAVITY_RIGHT)
        rlView.show()

    }

    /**
     * 显示纵向布局
     */
    private fun show2() {
        val verticalView = findViewById<VerticalView>(R.id.vertical_view)
        verticalView.setDefaultBack(false)
        //方法参数同（6）横向布局
        //方法参数同（6）横向布局
        verticalView.addLocation(14, Color.WHITE)
        verticalView.addTemp(14, Color.WHITE)
        verticalView.addWeatherIcon(14)
        verticalView.addCond(14, Color.WHITE)
        verticalView.addWindIcon(14)
        verticalView.addWind(14, Color.WHITE)
        verticalView.addAqiText(14, Color.WHITE)
        verticalView.addAqiQlty(14)
        verticalView.addAqiNum(14)
        verticalView.addAlarmIcon(14)
        verticalView.addAlarmTxt(14)
        verticalView.addRainIcon(14)
        verticalView.addRainDetail(14, Color.WHITE)
        verticalView.show()

    }


    override fun loadData() {
        super.loadData()
        QWeatherConfig.init("key", "location");
    }

    override fun initListener() {
        val url = "https://widget-page.qweather.net/h5/index.html?md=0123456&bg=1&lc=auto&key=35b939dfd29c445b8ade870fe6bbd938&demo=true&v=_1621682174863"
        findViewById<Button>(R.id.btnH5).setOnClickListener {
            Navigate.startInnerH5(mContext, url)
        }
    }

}

