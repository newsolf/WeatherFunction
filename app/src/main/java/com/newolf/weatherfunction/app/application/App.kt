package com.newolf.weatherfunction.app.application

import android.app.Application
import com.baidu.mapapi.SDKInitializer
import com.baidu.trace.LBSTraceClient
import com.baidu.trace.Trace
import com.baidu.trace.model.OnTraceListener
import com.baidu.trace.model.PushMessage
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.newolf.weatherfunction.BuildConfig
import com.newolf.weatherfunction.app.service.LocationService
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta


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
class App : Application() {
    lateinit var  mLocationService : LocationService
    override fun onCreate() {
        super.onCreate()
        initUtils(this)
        initBugly(this)
        initLocation(this)
        initTrace(this)
    }

    private fun initUtils(app: App) {
        Utils.init(app)
        LogUtils.Builder().setLogSwitch(BuildConfig.DEBUG)
    }

    private fun initBugly(context: Application) {
        Beta.installTinker(this)
        Bugly.init(context, BuildConfig.BUGLY_APP_ID, BuildConfig.DEBUG)
        Beta.autoInit = true
        Beta.autoDownloadOnWifi = true
    }

    private fun initTrace(app: App) {
        LogUtils.e(DeviceUtils.getAndroidID())
        LogUtils.e(DeviceUtils.getMacAddress())
        LogUtils.e(DeviceUtils.getManufacturer())
        LogUtils.e(DeviceUtils.getModel())
        val name =
            DeviceUtils.getManufacturer() + DeviceUtils.getModel() + DeviceUtils.getAndroidID()
        LogUtils.e("name = $name")
        val trace = Trace(216163, name, false)
        val traceClient = LBSTraceClient(app)
        traceClient.setInterval(5, 10)
        traceClient.startTrace(trace, object : OnTraceListener {
            override fun onStartGatherCallback(p0: Int, p1: String?) {
                LogUtils.e("$p1:$p0")
            }

            override fun onBindServiceCallback(p0: Int, p1: String?) {
                LogUtils.e("$p1:$p0")
            }

            override fun onInitBOSCallback(p0: Int, p1: String?) {
                LogUtils.e("$p1:$p0")
            }

            override fun onStopGatherCallback(p0: Int, p1: String?) {
                LogUtils.e("$p1:$p0")
            }

            override fun onPushCallback(p0: Byte, p1: PushMessage?) {
            }

            override fun onStartTraceCallback(p0: Int, p1: String?) {
                LogUtils.e("$p1:$p0")
                if (p0 == 0) {
                    traceClient.startGather(this)
                }
            }

            override fun onStopTraceCallback(p0: Int, p1: String?) {
                LogUtils.e("$p1:$p0")
            }
        })
    }

    private fun initLocation(app: App) {
        mLocationService = LocationService(app)
        SDKInitializer.initialize(app)
    }





}


