package com.annasolox.weather.data.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apikey: String): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter("appid", apikey)
            .addQueryParameter("units", "metric")
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

}