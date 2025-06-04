package com.annasolox.weather.domain

import com.annasolox.weather.core.Resource
import com.annasolox.weather.data.bbdd.model.City
import com.annasolox.weather.domain.model.ForecastDomain
import com.annasolox.weather.domain.model.WeatherDomain

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double, lang: String): WeatherDomain
    suspend fun getForecast(lat: Double, lon: Double, lang: String): List<ForecastDomain>
    suspend fun getCitiesByName(name: String, state: String?, country: String?): Resource<List<City>>
}