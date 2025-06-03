package com.annasolox.weather.data.api.model

data class WeatherResponse(
    val coord: Coord,
    val weather: Weather,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val name: String,
)
