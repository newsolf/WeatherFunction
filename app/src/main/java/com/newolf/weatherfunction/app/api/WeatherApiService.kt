package com.newolf.weatherfunction.app.api

import com.blankj.utilcode.util.NetworkUtils
import com.newolf.weatherfunction.BuildConfig
import com.newolf.weatherfunction.app.api.service.WeatherService
import com.newolf.weatherfunction.app.application.App
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.io.File

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
object WeatherApiService : BaseRetrofitClient() {
    val service by lazy { getService(WeatherService::class.java, BuildConfig.API_URL) }

//    private val cookieJar by lazy { PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.CONTEXT)) }
    override fun handleBuilder(builder: OkHttpClient.Builder) {
        val httpCacheDirectory = File(App.CONTEXT.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
    if (BuildConfig.DEBUG){
        builder.addInterceptor(ChuckInterceptor(App.CONTEXT))
    }
        builder.cache(cache)
//            .cookieJar(cookieJar)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!NetworkUtils.isAvailableByPing()) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val response = chain.proceed(request)
                if (!NetworkUtils.isAvailableByPing()) {
                    val maxAge = 60 * 60
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }

                response
            }
    }


}
