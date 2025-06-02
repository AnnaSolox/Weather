package com.annasolox.weather.ui.model

data class Weather(
    val date: String,
    val city: String,
    val country: String,
    val currentTemp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double,
    val title: String,
    val description: String,
    val sunrise: String,
    val sunset: String,
    val forecast: List<Forecast>
)
