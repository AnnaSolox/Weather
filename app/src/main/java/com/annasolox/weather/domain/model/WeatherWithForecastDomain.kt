package com.annasolox.weather.domain.model

data class WeatherWithForecastDomain(
    val weather: WeatherDomain,
    val forecast: List<DailyForecastDomain>
)

