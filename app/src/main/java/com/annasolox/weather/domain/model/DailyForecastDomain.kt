package com.annasolox.weather.domain.model

data class DailyForecastDomain(
    val date: String,
    val maxTemp: Double,
    val minTemp: Double,
    val description: String,
    val icon: String
)