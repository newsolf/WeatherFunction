package com.newolf.weatherfunction.app.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes

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
@SuppressLint("StaticFieldLeak")
object ResUtils {
    private lateinit var mContext: Context
    fun init(context:Context){
        mContext = context.applicationContext
    }

    fun getString(@StringRes resId:Int):String{
      return  mContext.resources.getString(resId)
    }

    fun getColor( resId: Int): Int {
        return  mContext.resources.getColor(resId)
    }
}