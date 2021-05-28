@file:Suppress("INACCESSIBLE_TYPE")

package com.newolf.weatherfunction.app.api

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http.RealResponseBody
import okio.*
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.EOFException
import java.nio.charset.Charset

class ResetfulApiInterceptor(val context: Context) : Interceptor {
    companion object {
        private val UTF8 = Charset.forName("UTF-8")
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response: Response
        try {
            response = chain.proceed(request)

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        val responseBody = response.body
        val contentLength = responseBody?.contentLength()
        var source = responseBody?.source()
        source?.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
        var buffer = source?.buffer()

        val headers = response.headers

        var gzippedLength: Long? = null
        if ("gzip".equals(headers.get("Content-Encoding"), ignoreCase = true)) {
            gzippedLength = buffer?.size
            var gzippedResponseBody: GzipSource? = null
            try {
                gzippedResponseBody = GzipSource(buffer!!.clone())
                buffer = Buffer()
                buffer.writeAll(gzippedResponseBody)
            } finally {
                gzippedResponseBody?.close()
            }
        }

        var charset: Charset? = UTF8
        val contentType = responseBody?.contentType()
        if (contentType != null) {
            charset = contentType.charset(UTF8)
        }

        if (!isPlaintext(buffer!!)) {
            return response
        }

        if (contentLength != 0L && charset != null) {
            val readString = buffer.clone().readString(charset)

            var jsonObject = JSONObject(readString)
            val data = JSONObject(readString)
            LogUtils.e(jsonObject)
            jsonObject.put("data", data)
//            var tempString = jsonObject.toString()
//        tempString =  tempString.replace("\\","")

//            jsonObject = JSONObject(tempString)
            LogUtils.e(jsonObject)

            val stringCharArray = jsonObject.toString().toByteArray()


            val builder = response.newBuilder()
            try {

                val clazz = Class.forName("okio.RealBufferedSource")
                val constructor = clazz.getDeclaredConstructor(Source::class.java)
                val source1 = ByteArrayInputStream(stringCharArray).source()
                constructor.isAccessible = true
                val instance = constructor.newInstance(source1)
                source = instance as BufferedSource?
                response = builder.body(
                    source?.let {
                        RealResponseBody(
                            responseBody?.contentType().toString(),
                            stringCharArray.size.toLong(),
                            it
                        )
                    }
                ).build()
            } catch (e: Exception) {
                e.printStackTrace()
                LogUtils.e(e, Exception())
            }

        }




        return response
    }


    fun isPlaintext(buffer: Buffer): Boolean {
        try {
            val prefix = Buffer()
            val byteCount = if (buffer.size < 64) buffer.size else 64
            buffer.copyTo(prefix, 0, byteCount)
            for (i in 0..15) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            return true
        } catch (e: EOFException) {
            return false // Truncated UTF-8 sequence.
        }

    }


}
