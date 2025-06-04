package com.annasolox.weather.data.bbdd.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey val id: Long,
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double
)
