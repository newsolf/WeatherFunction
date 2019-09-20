package com.newolf.weatherfunction.app.widget

import com.blankj.utilcode.util.LogUtils

interface IndicatorValueChangeListener {
    fun onChange(indicatorValue: Int, stateDescription: String, indicatorTextColor: Int) {
        LogUtils.e("indicatorValue = $indicatorValue , stateDescription = $stateDescription , indicatorTextColor = $indicatorTextColor")
    }

}
