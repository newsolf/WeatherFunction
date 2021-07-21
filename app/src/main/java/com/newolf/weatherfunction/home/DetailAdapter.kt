package com.newolf.weatherfunction.home


import com.newolf.library.adapt.base.BaseQuickAdapter
import com.newolf.library.adapt.base.viewholder.BaseViewHolder
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

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * 实现此方法，并使用 helper 完成 item 视图的操作
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseViewHolder, item: TodayDetail) {
        item?.res?.let { helper?.setImageResource(R.id.ivDetailIcon, it) }
        item?.detailKeyRes?.let { helper?.setText(R.id.tvDetailKey, it) }
        item?.detailValue?.let { helper?.setText(R.id.tvDetailValue, it) }
    }
}