package com.annasolox.weather.domain.model

data class ForecastDomain(
    val date: Long,
    val temperature: Double,
    val description: String,
    val icon: String
)
