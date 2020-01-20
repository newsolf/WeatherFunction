package com.newolf.weatherfunction.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.bean.LifeDetail

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
class LifeIndexAdapter(data: MutableList<LifeDetail>?) :
    BaseQuickAdapter<LifeDetail, BaseViewHolder>(R.layout.item_life_index, data) {

    override fun convert(helper: BaseViewHolder?, item: LifeDetail?) {
        item?.res?.let { helper?.setImageResource(R.id.ivIndexIcon, it) }
        item?.detailKeyRes?.let { helper?.setText(R.id.tvIndexName, it) }
        item?.detailValueLevel?.let { helper?.setText(R.id.tvIndexLevel, it) }
    }
}