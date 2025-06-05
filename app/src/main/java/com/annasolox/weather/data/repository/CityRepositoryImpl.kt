package com.annasolox.weather.data.repository

import com.annasolox.weather.data.bbdd.CityDao
import com.annasolox.weather.data.bbdd.model.City
import com.annasolox.weather.domain.CityRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityRepositoryImpl @Inject constructor(
    private val cityDao: CityDao
) : CityRepository {
    override fun searchCities(
        name: String,
        country: String?
    ): Flow<List<City>> {
        val safeCountry = country ?: ""
        return cityDao.searchCitiesByName(name, safeCountry).map { cities ->
            cities
                .distinctBy { "${it.name}, ${it.country}" }
        }
    }

    override fun getCityById(id: Long): City {
        return cityDao.getCityById(id)
    }
}