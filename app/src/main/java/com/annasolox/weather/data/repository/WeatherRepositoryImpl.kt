package com.annasolox.weather.data.repository

import com.annasolox.weather.core.Resource
import com.annasolox.weather.data.api.OpenWeatherApiService
import com.annasolox.weather.data.bbdd.CityDao
import com.annasolox.weather.data.bbdd.model.City
import com.annasolox.weather.domain.WeatherRepository
import com.annasolox.weather.domain.model.ForecastDomain
import com.annasolox.weather.domain.model.WeatherDomain
import com.annasolox.weather.mapper.ForecastMapper
import com.annasolox.weather.mapper.WeatherMapper
import java.util.Locale
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiService: OpenWeatherApiService,
    private val cityDao: CityDao
): WeatherRepository {

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        lang: String,
    ): WeatherDomain {
        val response = weatherApiService.getCurrentWeather(lat, lon, lang)
        return WeatherMapper.fromApiToDomain(response)
    }

    override suspend fun getForecast(
        lat: Double,
        lon: Double,
        lang: String,
    ): List<ForecastDomain> {
        val response = weatherApiService.getForecastWeather(lat, lon, lang)
        return ForecastMapper.fromApiToDomain(response)
    }

    override suspend fun getCitiesByName(
        name: String,
        state: String?,
        country: String?
    ): Resource<List<City>> {
        TODO("Not yet implemented")
    }


}