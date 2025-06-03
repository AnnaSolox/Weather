package com.annasolox.weather.data.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.annasolox.weather.data.bbdd.model.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao
}