package com.annasolox.weather.domain.model

data class CityDomain(
    val id: Long,
    val lat: Double,
    val lon: Double,
    val name: String,
    val country: String? = ""
)
