package com.annasolox.weather.ui.mapper

import com.annasolox.weather.data.api.model.WeatherResponse
import com.annasolox.weather.ui.model.Weather
import com.annasolox.weather.ui.mapper.utils.DateFormatter

object WeatherMapper {
    fun fromApiToUi(weatherApi: WeatherResponse): Weather {
        return Weather(
            date = DateFormatter.formatDate(weatherApi.dt),
            city = weatherApi.name,
            country = weatherApi.sys.country,
            currentTemp = weatherApi.main.temp,
            feelsLike = weatherApi.main.feelsLike,
            pressure = weatherApi.main.pressure,
            humidity = weatherApi.main.humidity,
            wind = weatherApi.wind.speed,
            title = weatherApi.weather.main,
            description = weatherApi.weather.description,
            sunrise = DateFormatter.formatTime(weatherApi.sys.sunrise),
            sunset = DateFormatter.formatTime(weatherApi.sys.sunset)
        )
    }
}