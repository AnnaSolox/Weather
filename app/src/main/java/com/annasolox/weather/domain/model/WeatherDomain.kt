package com.annasolox.weather.domain.model

data class WeatherDomain(
    val date: Long,
    val city: String,
    val country: String,
    val currentTemp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double,
    val title: String,
    val description: String,
    val sunrise: Long,
    val sunset: Long,
    val icon: String
)