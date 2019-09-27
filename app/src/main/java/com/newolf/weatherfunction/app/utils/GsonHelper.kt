package com.newolf.weatherfunction.app.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder


/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2019-09-25
 * 描述:
 * 历史:<br/>
 * ================================================
 */

object GsonHelper {
    private val sGson: Gson = GsonBuilder().create()
    fun getDefault(): Gson {
        return sGson
    }
}