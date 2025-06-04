package com.annasolox.weather.domain

import com.annasolox.weather.domain.model.CityDomain
import com.annasolox.weather.mapper.CityMapper
import javax.inject.Inject

class GetCityByIdUseCase @Inject constructor(
    private val repository: CityRepository
) {
    operator fun invoke(id: Long): CityDomain {
        val city = repository.getCityById(id)
        return CityMapper.fromDbToDomain(city)
    }
}
