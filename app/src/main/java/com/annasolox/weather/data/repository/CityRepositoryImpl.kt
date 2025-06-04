package com.annasolox.weather.data.repository

import com.annasolox.weather.data.bbdd.CityDao
import com.annasolox.weather.data.bbdd.model.City
import com.annasolox.weather.domain.CityRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class CityRepositoryImpl @Inject constructor(
    private val cityDao: CityDao
) : CityRepository {
    override fun searchCities(
        name: String,
        state: String?,
        country: String?
    ): Flow<List<City>> {
        return cityDao.searchCitiesByName(name, state, country)
    }
}