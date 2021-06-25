package com.newolf.weatherfunction.welcome

import android.text.TextUtils
import androidx.lifecycle.LifecycleOwner
import com.ewolf.wolfanim.RotateImageView
import com.newolf.library.adapt.base.BaseQuickAdapter
import com.newolf.library.adapt.base.viewholder.BaseViewHolder
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.Navigate

/**
 * NineAdapter
 *
 * @author NeWolf
 * @since 2021-06-25
 */
class NineAdapter(private val lifecycleOwner: LifecycleOwner, data: MutableList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_nine,data) {
    private val stringBuilder  = StringBuilder()


    override fun convert(holder: BaseViewHolder, item: String) {
        val rotateImageView =
            holder.getView<RotateImageView>(R.id.riv_show).addLifecycleObserver(lifecycleOwner)
        rotateImageView.stop()
        holder.itemView.setOnClickListener {
            if (rotateImageView.isAnimStart()){
                rotateImageView.stop()
            }else{
                rotateImageView.start()
            }
            stringBuilder.append(item)
            if (TextUtils.equals(stringBuilder.toString().toInt().toHexString(),"1850e7")){
                Navigate.startBlackActivity(context)
            }
        }

        when (item) {
            "1", "3", "7", "9" -> rotateImageView.start()
        }

    }

}