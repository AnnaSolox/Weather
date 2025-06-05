package com.annasolox.weather.data.api

import com.annasolox.weather.data.api.model.ForecastResponse
import com.annasolox.weather.data.api.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") language: String = "es",
    ): WeatherResponse

    @GET("forecast")
    suspend fun getForecastWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") language: String = "es",
    ): ForecastResponse
}