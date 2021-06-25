package com.newolf.weatherfunction.black

import androidx.lifecycle.LifecycleOwner
import com.ewolf.wolfanim.RotateImageView
import com.newolf.library.adapt.base.BaseQuickAdapter
import com.newolf.library.adapt.base.viewholder.BaseViewHolder
import com.newolf.weatherfunction.R

/**
 * BlackNineAdapter
 *
 * @author NeWolf
 * @since 2021-06-25
 */
class BlackNineAdapter(private val lifecycleOwner: LifecycleOwner, data: MutableList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_black_nine,data) {


    override fun convert(holder: BaseViewHolder, item: String) {
        val rotateImageView =
            holder.getView<RotateImageView>(R.id.riv_show).addLifecycleObserver(lifecycleOwner)
        holder.itemView.setOnClickListener {
            if (rotateImageView.isAnimStart()){
                rotateImageView.stop()
            }else{
                rotateImageView.start()
            }
        }



    }

}