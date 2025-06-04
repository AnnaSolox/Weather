package com.annasolox.weather.mapper

import com.annasolox.weather.data.bbdd.model.City
import com.annasolox.weather.domain.model.CityDomain
import com.annasolox.weather.ui.model.CityUi

object CityMapper {
    fun fromDbToDomain(city: City): CityDomain{
        return CityDomain(
            id = city.id,
            name = city.name,
            country = city.country,
            lat = city.lat,
            lon = city.lon
        )
    }

    fun fromDomainToUi(cityDomain: CityDomain): CityUi {
        val completeCityName = listOfNotNull(cityDomain.name, cityDomain.country).joinToString(", ")
        return CityUi(
            id = cityDomain.id,
            completeName = completeCityName
        )
    }
}