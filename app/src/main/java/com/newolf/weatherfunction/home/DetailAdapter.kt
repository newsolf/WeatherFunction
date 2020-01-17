package com.newolf.weatherfunction.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.bean.TodayDetail

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2020-01-17
 * 描述:
 * 历史:<br/>
 * ================================================
 */
class DetailAdapter(data: MutableList<TodayDetail>?) :
    BaseQuickAdapter<TodayDetail, BaseViewHolder>(R.layout.item_detail, data) {

    override fun convert(helper: BaseViewHolder?, item: TodayDetail?) {
        item?.res?.let { helper?.setImageResource(R.id.ivDetailIcon, it) }
        item?.detailKeyRes?.let { helper?.setText(R.id.tvDetailKey, it) }
        item?.detailValue?.let { helper?.setText(R.id.tvDetailValue, it) }
    }
}