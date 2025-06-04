package com.annasolox.weather.ui.model

data class WeatherUi(
    val date: String,
    val city: String,
    val country: String,
    val currentTemp: Int,
    val feelsLike: Int,
    val pressure: Int,
    val humidity: Int,
    val wind: Double,
    val title: String,
    val description: String,
    val sunrise: String,
    val sunset: String,
    val forecast: List<ForecastUi>
)
