package com.annasolox.weather.data.bbdd.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey val id: Int,
    val name: String,
    val state: String? = null,
    val country: String,
    @ColumnInfo(name="coord_lat")
    val lat: Double,
    @ColumnInfo(name="coord_lon")
    val lon: Double
)
