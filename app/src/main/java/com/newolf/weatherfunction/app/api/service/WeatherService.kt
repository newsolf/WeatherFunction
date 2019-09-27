package com.newolf.weatherfunction.app.api.service

import com.newolf.weatherfunction.app.api.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


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
public interface WeatherService {
    /**
     * 获取指定城市的实时天气
     *
     *
     * API地址：http://service.envicloud.cn:8082/v2/weatherlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101020100
     *
     * @param cityId 城市id
     * @return Observable
     */
    @GET("/v2/weatherlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    fun getWeatherLive(@Path("cityId") cityId: String): Observable<String>

    /**
     * 获取指定城市7日天气预报
     *
     *
     * API地址：http://service.envicloud.cn:8082/v2/weatherforecast/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101020100
     *
     * @param cityId 城市id
     * @return Observable
     */
    @GET("/v2/weatherforecast/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    fun getWeatherForecast(@Path("cityId") cityId: String): Observable<String>

    /**
     * 获取指定城市的实时空气质量
     *
     *
     * API地址：http://service.envicloud.cn:8082/v2/cityairlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101020100
     *
     * @param cityId 城市id
     * @return Observable
     */

    @GET("/v2/cityairlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    fun getAirLive(@Path("cityId") cityId: String): Observable<String>

    /**
     *
     * @param cityId
     * @return http://service.envicloud.cn:8082/v2/citycode/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU= {lng}/{lat}
     */
    @GET("v2/locate/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{lng}/{lat}")
    suspend fun getCityCode(@Path("lng") lng: String,@Path("lat")lat: String): BaseResponse
    /**
     *
     * @param cityId
     * @return http://service.envicloud.cn:8082/v2/citycode/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/北京
     */
    @GET("/v2/citycode/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityName}")
    suspend fun getCityCodeByCityName(@Path("cityName") lng: String): BaseResponse
}