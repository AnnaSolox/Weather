package com.annasolox.weather.mapper

import com.annasolox.weather.R

object WeatherIconMapper {
    fun mapIconToDrawable(icon: String): Int {
        return when (icon){
            "01d", "01n" -> R.drawable.sun_icon
            "02d", "02n" -> R.drawable.few_clouds_icon
            "03d", "03n" -> R.drawable.cloud_icon
            "04d", "04n" -> R.drawable.broken_clouds_icon
            "09d", "09n" -> R.drawable.shower_rain_icon
            "10d", "10n" -> R.drawable.rain_icon
            "11d", "11n" -> R.drawable.storm_icon
            "13d", "13n" -> R.drawable.snow_icon
            "50d", "50n" -> R.drawable.mist_icon
            else -> R.drawable.sun_icon
        }
    }
}