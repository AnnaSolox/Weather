package com.annasolox.weather.domain

import android.util.Log
import com.annasolox.weather.core.Resource
import com.annasolox.weather.domain.model.DailyForecastDomain
import com.annasolox.weather.domain.model.WeatherWithForecastDomain
import com.annasolox.weather.mapper.utils.DateFormatter
import java.time.Instant
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(lat: Double, lon: Double, lang: String): Resource<WeatherWithForecastDomain> {
        return try{
            val weather = repository.getCurrentWeather(lat, lon, lang)
            val forecastList = repository.getForecast(lat, lon, lang)

            val now = Instant.now().epochSecond
            val today = DateFormatter.formatDate(now)
            Log.d("GetWeatherUseCase", "Today: $today")

            var dailyForecast = forecastList
                .sortedBy { it.date }
                .groupBy { DateFormatter.formatDate(it.date) }
                .map { (date, entries) ->
                    DailyForecastDomain(
                        date = date,
                        maxTemp = entries.maxOf { it.temperature },
                        minTemp = entries.minOf { it.temperature },
                        description = entries
                            .groupingBy { it.description }
                            .eachCount()
                            .maxByOrNull { it.value }
                            ?.key ?: "",
                        icon = entries.
                            groupBy { it.icon }
                            .maxByOrNull { it.value.size }
                            ?.key ?: ""
                    )
                }

            Log.d("GetWeatherUseCase", "Daily forecast: $dailyForecast")

            dailyForecast =
                if (dailyForecast.first().date == today) {
                 dailyForecast.subList(1, 5)
            } else dailyForecast.take(4)

            val weatherWithForecastDomain = WeatherWithForecastDomain(
                weather = weather,
                forecast = dailyForecast
            )

            Resource.Success(weatherWithForecastDomain)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}