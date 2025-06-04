package com.annasolox.weather.mapper

import com.annasolox.weather.data.api.model.WeatherResponse
import com.annasolox.weather.domain.model.WeatherDomain
import com.annasolox.weather.domain.model.WeatherWithForecastDomain
import com.annasolox.weather.ui.model.WeatherUi
import com.annasolox.weather.mapper.utils.DateFormatter
import kotlin.math.roundToInt

object WeatherMapper {
    fun fromApiToDomain(weatherApi: WeatherResponse): WeatherDomain {
        return WeatherDomain(
            date = weatherApi.dt,
            city = weatherApi.name,
            country = weatherApi.sys.country,
            currentTemp = weatherApi.main.temp,
            feelsLike = weatherApi.main.feelsLike,
            pressure = weatherApi.main.pressure,
            humidity = weatherApi.main.humidity,
            wind = weatherApi.wind.speed,
            title = weatherApi.weather.first().main,
            description = weatherApi.weather.first().description,
            sunrise = weatherApi.sys.sunrise,
            sunset = weatherApi.sys.sunset,
            icon = weatherApi.weather.first().icon
        )
    }

    fun fromDomainToUi(weatherWithForecastDomain: WeatherWithForecastDomain): WeatherUi {
        val weather = weatherWithForecastDomain.weather
        val forecastList = weatherWithForecastDomain.forecast

        return WeatherUi(
            date = DateFormatter.formatDate(weather.date),
            city = weather.city,
            country = weather.country,
            currentTemp = weather.currentTemp.roundToInt(),
            feelsLike = weather.feelsLike.roundToInt(),
            pressure = weather.pressure,
            humidity = weather.humidity,
            wind = weather.wind,
            title = weather.title,
            description = weather.description,
            sunrise = DateFormatter.formatTime(weather.sunrise),
            sunset = DateFormatter.formatTime(weather.sunset),
            forecast = forecastList.map { ForecastMapper.fromDomainToUi(it) },
            icon = WeatherIconMapper.mapIconToDrawable(weather.icon)
        )
    }
}