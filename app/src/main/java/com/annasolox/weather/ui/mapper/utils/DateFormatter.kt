package com.annasolox.weather.ui.mapper.utils

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.Instant

object DateFormatter{
    private val dateFormatter = DateTimeFormatter.ofPattern("EEE, dd/MM/yyyy")
        .withZone(ZoneId.systemDefault())

    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        .withZone(ZoneId.systemDefault())

    fun formatDate(dt: Long): String {
        val raw = dateFormatter.format(Instant.ofEpochSecond(dt))
        val parts = raw.split(", ")

        if(parts.size == 2){
            val dayAbbr = parts[0]
                .replace(".", "")
                .take(3)
                .replaceFirstChar { it.uppercase() }
            return "$dayAbbr, ${parts[1]}"
        }
        return raw
    }

    fun formatTime(dt: Long): String {
        return timeFormatter.format(Instant.ofEpochSecond(dt))
    }
}