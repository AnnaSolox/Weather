package com.annasolox.weather.domain

import com.annasolox.weather.data.bbdd.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun searchCities(name: String, state: String?, country: String?): Flow<List<City>>
}