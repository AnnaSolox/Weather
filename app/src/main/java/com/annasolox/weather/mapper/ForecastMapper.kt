package com.annasolox.weather.mapper

import com.annasolox.weather.data.api.model.ForecastResponse
import com.annasolox.weather.domain.model.DailyForecastDomain
import com.annasolox.weather.domain.model.ForecastDomain
import com.annasolox.weather.ui.model.ForecastUi
import kotlin.math.roundToInt

object ForecastMapper {
    fun fromApiToDomain(forecastResponse: ForecastResponse): List<ForecastDomain> {
        val forecastDomain = forecastResponse.list.map { forecast ->
            ForecastDomain(
                date = forecast.dt,
                temperature = forecast.main.temp,
                description = forecast.weather.first().description,
                icon = forecast.weather.first().icon
            )
        }
        return forecastDomain
    }

    fun fromDomainToUi(dailyForecastDomain: DailyForecastDomain): ForecastUi {
        return ForecastUi(
            date = dailyForecastDomain.date,
            maxTemp = dailyForecastDomain.maxTemp.roundToInt(),
            minTemp = dailyForecastDomain.minTemp.roundToInt(),
            description = dailyForecastDomain.description,
            icon = WeatherIconMapper.mapIconToDrawable(dailyForecastDomain.icon)
        )
    }
}