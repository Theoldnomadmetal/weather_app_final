package com.example.weather_app_final.data

import com.example.weather_app_final.data.current.CurrentModel
import com.example.weather_app_final.data.forecast2.ForecastModel2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {


    @GET("data/2.5/weather")
    fun getCurrent(@Query("q") city:String,
                   @Query("appId")appId :String,
                   @Query("units")units : String): Call<CurrentModel>


    @GET("data/2.5/forecast")
    fun getSecondForecast(
        @Query("q") city : String,
        @Query("appid") appId: String,
        @Query("units")units : String
    ): Call<ForecastModel2>


}

//https://api.openweathermap.org/data/2.5/onecall?lat=42.8700000&lon=74.5900000&%20exclude=daily&appid=b35b9e82fd3a41f4abcadcdbb0768f8b