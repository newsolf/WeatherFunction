package com.newolf.weatherfunction.app.api

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2019-09-26
 * 描述:
 * 历史:<br/>
 * ================================================
 */
open class BaseRepository {
    suspend fun <T : Any> apiCall(call: suspend () -> BaseResponse<T>): BaseResponse<T> {
        return call.invoke()
    }
//    suspend fun <T : Any> apiCall(call: suspend () -> T): T {
//
//    LogUtils.e("" + call)
//    val invoke = call.invoke()
//    LogUtils.e(invoke)
//    return invoke
//    }
}