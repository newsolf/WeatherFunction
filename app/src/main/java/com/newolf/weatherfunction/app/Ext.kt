package com.newolf.weatherfunction.app

import android.app.Activity
import com.blankj.utilcode.util.ToastUtils
import com.newolf.weatherfunction.app.api.BaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2019-09-27
 * 描述:
 * 历史:<br/>
 * ================================================
 */

suspend fun executeResponse(response: BaseResponse<Any>, successBlock: suspend CoroutineScope.() -> Unit,
                            errorBlock: suspend CoroutineScope.() -> Unit) {
    coroutineScope {
        if (response.rcode != 200) errorBlock()
        else successBlock()
    }
}

fun Activity.onNetError(e: Throwable, func: (e: Throwable) -> Unit) {
    if (e is HttpException) {
        ToastUtils.showShort("请求错误")
        func(e)
    }
}