package com.annasolox.weather.ui.mapper.utils

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.Instant

object DateFormatter{
    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        .withZone(ZoneId.systemDefault())

    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        .withZone(ZoneId.systemDefault())

    fun formatDate(dt: Long): String {
        return dateFormatter.format(Instant.ofEpochSecond(dt))
    }

    fun formatTime(dt: Long): String {
        return timeFormatter.format(Instant.ofEpochSecond(dt))
    }
}