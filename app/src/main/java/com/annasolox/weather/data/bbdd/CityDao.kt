package com.annasolox.weather.data.bbdd

import androidx.room.Dao
import androidx.room.Query
import com.annasolox.weather.data.bbdd.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao{
    @Query("SELECT * FROM cities " +
            "WHERE name LIKE :name || '%'" +
            "AND (state LIKE :state || '%' OR :state IS NULL OR :state = '')" +
            "AND country LIKE :country || '%'")
    fun searchCitiesByName(name: String, state: String?, country: String?): Flow<List<City>>
}