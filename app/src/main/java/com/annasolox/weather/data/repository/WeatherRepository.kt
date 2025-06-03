package com.annasolox.weather.data.repository

import android.util.Log
import com.annasolox.weather.core.Resource
import com.annasolox.weather.data.api.OpenWeatherApiService
import com.annasolox.weather.data.bbdd.CityDao
import com.annasolox.weather.data.bbdd.model.City
import com.annasolox.weather.ui.mapper.ForecastMapper
import com.annasolox.weather.ui.mapper.WeatherMapper
import com.annasolox.weather.ui.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApiService: OpenWeatherApiService,
    private val cityDao: CityDao
) {
    suspend fun getWeather(lat: Double, lon: Double, language: String): Resource<Weather> =
        withContext(Dispatchers.IO) {
            try {
                val currentWeather = weatherApiService.getCurrentWeather(lat, lon, language)
                val forecast = weatherApiService.getForecastWeather(lat, lon, language)
                Log.d("WeatherRepository", "Forecast: ${forecast.list.last()}")

                val weatherUi = WeatherMapper.fromApiToUi(currentWeather)
                val forecastUi = ForecastMapper.fromApiToUi(forecast).drop(1).take(4)
                weatherUi.forecast = forecastUi

                Resource.Success(weatherUi)
            } catch (e: Exception) {
                Resource.Error(e)
            }
        }

    fun getCitiesByName(
        name: String,
        state: String?,
        country: String?
    ): Flow<Resource<List<City>>> =
        cityDao.searchCitiesByName(name, state ?: "", country ?: "")
            .map<List<City>, Resource<List<City>>> { Resource.Success(it) }
            .onStart { emit(Resource.Loading) }
            .catch { e -> emit(Resource.Error(e)) }
            .flowOn(Dispatchers.IO)
}