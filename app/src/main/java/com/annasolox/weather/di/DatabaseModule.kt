package com.annasolox.weather.di

import android.content.Context
import androidx.room.Room
import com.annasolox.weather.data.bbdd.CityDao
import com.annasolox.weather.data.bbdd.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "cities.db"

    @Provides
    @Singleton
    fun provideWeatherDatabase(
        @ApplicationContext appContext: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            appContext,
            WeatherDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .createFromAsset(DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideCityDao(database: WeatherDatabase): CityDao {
        return database.cityDao()
    }
}