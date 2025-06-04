package com.annasolox.weather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annasolox.weather.core.Resource
import com.annasolox.weather.domain.GetWeatherUseCase
import com.annasolox.weather.mapper.WeatherMapper
import com.annasolox.weather.ui.model.WeatherUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    val lang: String = Locale.getDefault().language

    private val _weather = MutableLiveData<Resource<WeatherUi>>(Resource.Loading)
    val weather: LiveData<Resource<WeatherUi>> = _weather

    init {
        getWeather(39.9333, -0.1)
    }

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weather.value = Resource.Loading
            val result = getWeatherUseCase(lat, lon, lang)

            _weather.value =
                when (result) {
                    is Resource.Success -> {
                        val weatherUi = WeatherMapper.fromDomainToUi(result.data)
                        Resource.Success(weatherUi)
                    }

                    is Resource.Error -> Resource.Error(result.exception)
                    is Resource.Loading -> Resource.Loading
                }
        }
    }
}
