package com.newolf.weatherfunction.app.api

import android.content.Context
import com.newolf.weatherfunction.BuildConfig
import com.newolf.weatherfunction.app.api.service.WeatherService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2019/9/25
 * 描述:
 * 历史:<br/>
 * ================================================
 */
object ApiService {
    val API_URL = BuildConfig.API_URL
    private const val CONNECT_TIME_OUT = 15
    private const val READ_TIME_OUT = 20
    private const val WRITE_TIME_OUT = 20
    private const val DISK_CACHE_SIZE = 50 * 1024 * 1024 // 50MB

    private lateinit var sRetrofit: Retrofit

    fun init(context: Context) {
        sRetrofit = Retrofit.Builder()
            .client(buildOkHttpClient(context.applicationContext))
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(API_URL)
            .build()
    }

    private fun buildOkHttpClient(context: Context): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor(ApiHeaderInterceptor())
//        httpClient.addInterceptor(new HeaderInterceptor());
//        if (BuildConfig.DEBUG) {
//            val logInterceptor = HttpLoggingInterceptor()
//            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            httpClient.addInterceptor(logInterceptor)
////            httpClient.addInterceptor(ChuckInterceptor(context))
//        }





        val cacheDir = File(context.cacheDir, "http")
        val cache = Cache(cacheDir, DISK_CACHE_SIZE.toLong())
        httpClient.cache(cache)

        //设置缓存
//        httpClient.addNetworkInterceptor(cacheInterceptor)
//        httpClient.addInterceptor(cacheInterceptor)
        httpClient.cache(cache)
        //设置超时
        httpClient.connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
        httpClient.readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
        httpClient.writeTimeout(WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)


        //错误重连
        httpClient.retryOnConnectionFailure(true)
        return httpClient.build()
    }

    fun getRetrofit(): Retrofit {
        return sRetrofit
    }

    fun getWeatherService(): WeatherService {
        return getRetrofit().create(WeatherService::class.java)
    }

}