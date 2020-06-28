package com.newolf.weatherfunction.app.utils

import android.annotation.SuppressLint
import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.R

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2020-02-03
 * 描述:
 * 历史:<br/>
 * ================================================
 */
@SuppressLint("StaticFieldLeak")
object WeatherResUtils {
    private lateinit var mContext: Context
    fun init(context: Context){
        mContext = context.applicationContext
    }

    /**
     * 默认为晴，不在列表中也是晴
     */
    fun getWeatherRes( weatherStr:String):Int{
        var weatherRes = R.mipmap.sunny
        when (weatherStr) {
            "晴" ->weatherRes = R.mipmap.sunny
            "多云" -> weatherRes = R.mipmap.cloudy
            "阴" -> weatherRes = R.mipmap.overcast
            "阵雨" -> weatherRes = R.mipmap.shower
            "雷阵雨" -> weatherRes = R.mipmap.thundershower
            "雷阵雨伴有冰雹" -> weatherRes = R.mipmap.thundershower
            "雨夹雪" -> weatherRes = R.mipmap.sleet
            "小雨" -> weatherRes = R.mipmap.lightrain
            "中雨" -> weatherRes = R.mipmap.moderaterain
            "冻雨" -> weatherRes = R.mipmap.hail
            "大雨" -> weatherRes = R.mipmap.heavyrain
            "暴雨","大暴雨","特大暴雨" -> weatherRes = R.mipmap.storm
            "阵雪" -> weatherRes = R.mipmap.snowflurry
            "小雪" -> weatherRes = R.mipmap.lightsnow
            "中雪" -> weatherRes = R.mipmap.moderatesnow
            "大雪" -> weatherRes = R.mipmap.heavysnow
            "暴雪" -> weatherRes = R.mipmap.blizzard
            "雾" -> weatherRes = R.mipmap.foggy
            "浮尘" -> weatherRes = R.mipmap.dust
            "扬沙" -> weatherRes = R.mipmap.sand
            "沙尘暴","强沙尘暴" -> weatherRes = R.mipmap.duststorm
            "霾" -> weatherRes = R.mipmap.haze
            else -> {
                LogUtils.e("$weatherStr 不在列表中，已返回默认晴天res")
            }
        }
        LogUtils.e("$weatherStr 对应图片为: $weatherRes")

        return  weatherRes
    }
}