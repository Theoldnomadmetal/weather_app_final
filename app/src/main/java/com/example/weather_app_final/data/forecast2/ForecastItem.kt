package com.example.weather_app_final.data.forecast2

class ForecastItem (
    val dt : Int,
    val visibility : Int,
    val pop : Double,
    val dt_txt : String,
    val main: Main,
    val weather : List<WeatherItem>
)