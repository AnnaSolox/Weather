package com.annasolox.weather.data.repository

import com.annasolox.weather.data.api.OpenWeatherApiService
import com.annasolox.weather.data.api.model.ForecastResponse
import com.annasolox.weather.data.api.model.WeatherResponse
import com.annasolox.weather.data.bbdd.CityDao
import com.annasolox.weather.data.bbdd.model.City
import kotlinx.coroutines.flow.Flow

class WeatherRepository(
    private val weatherApiService: OpenWeatherApiService,
    private val cityDao: CityDao
    ) {
    //TODO: This method should return a Weather object
    suspend fun getWeather(lat: Double, lon: Double, language: String){
        val currentWeather = weatherApiService.getCurrentWeather(lat, lon, language)
        val forecast = weatherApiService.getForecastWeather(lat, lon, language)
    }

    suspend fun getCitiesByName(name: String, state: String?, country: String?): Flow<List<City>> {
        return cityDao.searchCitiesByName(name, state ?: "", country ?: "")
    }
}