package com.newolf.weatherfunction.home

import android.text.TextUtils
import com.blankj.utilcode.util.TimeUtils
import com.newolf.library.adapt.base.BaseQuickAdapter
import com.newolf.library.adapt.base.viewholder.BaseViewHolder

import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.utils.ResUtils
import com.newolf.weatherfunction.app.utils.WeatherResUtils
import com.newolf.weatherfunction.model.Forecast

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2020-01-19
 * 描述:
 * 历史:<br/>
 * ================================================
 */
class ForecastAdapter(data: MutableList<Forecast>?) :
    BaseQuickAdapter<Forecast, BaseViewHolder>(R.layout.item_forecast, data) {

    override fun convert(helper: BaseViewHolder, item: Forecast) {
        item.date.let {
            helper.setText(R.id.tvDate, it)
            helper.setText(R.id.tvWeek, TimeUtils.getChineseWeek("$it 08:08:08"))
            if (helper.position == 0) {
                helper.setText(R.id.tvWeek, R.string.forecast_adapter_week_today)
            }
        }
        item.tmp.min.let { helper.setText(R.id.tvTempMin, it) }
         helper.setText(R.id.tvSeparator, "~")
        item.tmp.max.let { helper.setText(R.id.tvTempMax, it) }

        item.cond.cond_n.let {
            var weather :String
            val condDay = item.cond.cond_d
            weather = if (TextUtils.equals(it,condDay)){
                it
            }else{
                "$it 转 $condDay"
            }
            helper.setText(R.id.tvWeather, weather)
            helper.setImageResource(R.id.ivWeatherIcon, WeatherResUtils.getWeatherRes(weather))
            helper.setBackgroundColor(R.id.ivWeatherIcon,ResUtils.getColor(R.color.colorAccent) )
        }
    }


}