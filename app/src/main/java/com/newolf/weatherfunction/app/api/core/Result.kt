package com.newolf.weatherfunction.app.api.core

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
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}