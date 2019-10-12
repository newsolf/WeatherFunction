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
     * API地址：http://service.envicloud.cn:8082/v2/weatherlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101010100
     *
     * @param cityId 城市id
     * @return Observable
     *
     * response ： {"windspeed":"2.1","citycode":"101010100","airpressure":"1019.0","phenomena":"多云","rdesc":"Success","humidity":"45.0","updatetime":"2019-10-12 19:00","windpower":"微风","feelst":"15.8","winddirect":"东北风","rcode":200,"rain":"0.0","temperature":"15.8"}
     */
    @GET("/v2/weatherlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    fun getWeatherLive(@Path("cityId") cityId: String): Observable<String>

    /**
     * 获取指定城市7日天气预报
     *
     *
     * API地址：http://service.envicloud.cn:8082/v2/weatherforecast/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101010100
     *
     * @param cityId 城市id
     * @return Observable
     *
     * response ： {"citycode":"101010100","rdesc":"Success","forecast":[{"wind":{"dir":"东风","deg":"93","sc":"1-2","spd":"9"},"hum":"25","pcpn":"0.0","astro":{"mr":"17:20","sr":"06:22","ms":"04:24","ss":"17:38"},"uv":"4","tmp":{"min":"11","max":"18"},"pop":"2","date":"2019-10-12","pres":"1018","cond":{"cond_n":"多云","cond_d":"多云"},"vis":"25"},{"wind":{"dir":"西风","deg":"274","sc":"1-2","spd":"1"},"hum":"79","pcpn":"0.0","astro":{"mr":"17:45","sr":"06:23","ms":"05:23","ss":"17:36"},"uv":"4","tmp":{"min":"6","max":"15"},"pop":"0","date":"2019-10-13","pres":"1024","cond":{"cond_n":"晴","cond_d":"晴"},"vis":"17"},{"wind":{"dir":"南风","deg":"189","sc":"1-2","spd":"2"},"hum":"50","pcpn":"0.0","astro":{"mr":"18:11","sr":"06:24","ms":"06:21","ss":"17:35"},"uv":"4","tmp":{"min":"7","max":"16"},"pop":"0","date":"2019-10-14","pres":"1029","cond":{"cond_n":"多云","cond_d":"晴"},"vis":"24"},{"wind":{"dir":"西南风","deg":"247","sc":"1-2","spd":"6"},"hum":"20","pcpn":"0.0","astro":{"mr":"18:38","sr":"06:25","ms":"07:20","ss":"17:33"},"uv":"4","tmp":{"min":"7","max":"18"},"pop":"0","date":"2019-10-15","pres":"1021","cond":{"cond_n":"晴","cond_d":"晴"},"vis":"25"},{"wind":{"dir":"东南风","deg":"130","sc":"1-2","spd":"6"},"hum":"51","pcpn":"0.0","astro":{"mr":"19:08","sr":"06:26","ms":"08:20","ss":"17:32"},"uv":"1","tmp":{"min":"7","max":"13"},"pop":"21","date":"2019-10-16","pres":"1018","cond":{"cond_n":"小雨","cond_d":"小雨"},"vis":"25"},{"wind":{"dir":"东北风","deg":"70","sc":"1-2","spd":"9"},"hum":"56","pcpn":"0.0","astro":{"mr":"19:42","sr":"06:27","ms":"09:22","ss":"17:30"},"uv":"4","tmp":{"min":"8","max":"17"},"pop":"3","date":"2019-10-17","pres":"1017","cond":{"cond_n":"多云","cond_d":"多云"},"vis":"25"}],"rcode":200,"suggestion":{"trav":{"brf":"一般","txt":"天气较好，同时又有微风伴您一路同行，但是比较热，外出旅游请注意防晒，并注意防暑降温。"},"uv":{"brf":"很强","txt":"紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},"comf":{"brf":"很不舒适","txt":"白天天气晴好，但烈日炎炎会使您会感到很热，很不舒适。"},"sport":{"brf":"较不宜","txt":"天气较好，但炎热，请注意适当减少运动时间并降低运动强度，户外运动请注意防晒。"},"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drs":{"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"}},"cityname":"北京"}
     */
    @GET("/v2/weatherforecast/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    fun getWeatherForecast(@Path("cityId") cityId: String): Observable<String>

    /**
     * 获取指定城市的实时空气质量
     *
     *
     * API地址：http://service.envicloud.cn:8082/v2/cityairlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101010100
     *
     * @param cityId 城市id
     * @return Observable
     *
     * response ：{"citycode":"101010100","PM25":"33","time":"2019101219","rdesc":"Success","PM10":"78","SO2":"21.25","o3":"8.25","NO2":"62.75","primary":"PM10","rcode":200,"CO":"1.93","AQI":"64"}
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