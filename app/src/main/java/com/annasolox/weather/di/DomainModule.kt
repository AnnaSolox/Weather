package com.annasolox.weather.di

import com.annasolox.weather.data.repository.CityRepositoryImpl
import com.annasolox.weather.data.repository.WeatherRepositoryImpl
import com.annasolox.weather.domain.CityRepository
import com.annasolox.weather.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindWeatherRepository(
        impl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    fun bindCityRepository(
        impl: CityRepositoryImpl
    ): CityRepository
}