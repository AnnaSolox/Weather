package com.annasolox.weather.di

import com.annasolox.weather.BuildConfig
import com.annasolox.weather.data.api.ApiKeyInterceptor
import com.annasolox.weather.data.api.OpenWeatherApiService
import com.annasolox.weather.di.qualifier.Apikey
import com.annasolox.weather.di.qualifier.Baseurl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Apikey
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Baseurl
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        @Baseurl baseUrl: String
    ): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): OpenWeatherApiService {
        return retrofit.create(OpenWeatherApiService::class.java)
    }
}