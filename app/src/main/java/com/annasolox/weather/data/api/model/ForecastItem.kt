package com.annasolox.weather.data.api.model

data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>
)
