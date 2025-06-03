package com.annasolox.weather.ui.mapper

import com.annasolox.weather.data.api.model.ForecastResponse
import com.annasolox.weather.ui.model.Forecast
import com.annasolox.weather.ui.mapper.utils.DateFormatter

object ForecastMapper {
    fun fromApiToUi(forecastResponse: ForecastResponse): List<Forecast>{
        val groupedResponseForecast = forecastResponse.list.sortedBy { it.dt }.groupBy { DateFormatter.formatDate(it.dt) }
        val forecastList = groupedResponseForecast.map { entry ->
            val date = entry.key
            val items = entry.value

            val minTemp = items.minOf { it.main.temp }
            val maxTemp = items.maxOf { it.main.temp }

            val title = items.mapNotNull { it.weather.firstOrNull()?.main }
                .groupingBy { it }
                .eachCount()
                .maxByOrNull { it.value }
                ?.key ?: "Desconocido"

            Forecast(
                date = date,
                maxTemp = maxTemp,
                minTemp = minTemp,
                title = title
            )
        }
        return forecastList
    }
}