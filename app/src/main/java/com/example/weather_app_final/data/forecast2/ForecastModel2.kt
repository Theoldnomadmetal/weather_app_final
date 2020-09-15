package com.example.weather_app_final.data.forecast2

data class ForecastModel2 (
    val cod : String,
    val message : Int,
    val cnt : Int,
    val city: City,
    val list : List<ForecastItem>


)