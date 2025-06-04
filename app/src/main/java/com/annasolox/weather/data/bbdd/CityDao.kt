package com.annasolox.weather.data.bbdd

import androidx.room.Dao
import androidx.room.Query
import com.annasolox.weather.data.bbdd.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao{
    @Query("""
    SELECT * FROM cities
    WHERE name LIKE :name || '%'
    AND (:country IS NULL OR country LIKE :country || '%')
""")
    fun searchCitiesByName(name: String, country: String?): Flow<List<City>>

    @Query("""
        SELECT * FROM cities
        WHERE id = :id
    """)
    fun getCityById(id: Long): City
}