package com.annasolox.weather.core

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading: Resource<Nothing>()
}
