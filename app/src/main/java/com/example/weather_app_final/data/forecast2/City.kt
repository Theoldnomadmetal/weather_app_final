package com.example.weather_app_final.data.forecast2

data class City (
    val id : Int,
    val name : String,
    val coord: Coord,
    val country : String,
    val population : Int,
    val timezone : Int,
    val sunrise : Int,
    val sunset : Int
)