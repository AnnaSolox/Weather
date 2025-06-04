package com.annasolox.weather.domain

import com.annasolox.weather.domain.model.ForecastDomain
import com.annasolox.weather.domain.model.WeatherDomain

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double, lang: String): WeatherDomain
    suspend fun getForecast(lat: Double, lon: Double, lang: String): List<ForecastDomain> }