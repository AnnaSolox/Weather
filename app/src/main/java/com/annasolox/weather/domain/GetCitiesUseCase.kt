package com.annasolox.weather.domain

import com.annasolox.weather.domain.model.CityDomain
import com.annasolox.weather.mapper.CityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val repository: CityRepository) {
    operator fun invoke(
        name: String,
        country: String? = ""
    ): Flow<List<CityDomain>> {
        val response = repository.searchCities(name, country)
        val mappedResponse = response.map { cities ->
            cities.map { city ->
                CityMapper.fromDbToDomain(city)
            }
        }
        return mappedResponse
    }
}