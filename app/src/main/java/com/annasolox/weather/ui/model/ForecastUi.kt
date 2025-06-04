package com.annasolox.weather.ui.model

data class ForecastUi(
    val date: String,
    val maxTemp: Int,
    val minTemp: Int,
    val description: String,
    val icon: Int
)
