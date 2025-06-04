package com.annasolox.weather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annasolox.weather.core.Resource
import com.annasolox.weather.data.bbdd.model.City
import com.annasolox.weather.domain.GetWeatherUseCase
import com.annasolox.weather.mapper.WeatherMapper
import com.annasolox.weather.ui.model.WeatherUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    val lang: String = Locale.getDefault().language

    private val _cities = MutableStateFlow<Resource<List<City>>>(Resource.Loading)
    val cities: StateFlow<Resource<List<City>>> = _cities

    private val _weather = MutableLiveData<Resource<WeatherUi>>(Resource.Loading)
    val weather: LiveData<Resource<WeatherUi>> = _weather

    /*fun searchCities(name: String, state: String?, country: String?){
        viewModelScope.launch {
            repository.getCitiesByName(name, state, country)
                .collect { _cities.value = it }
        }
    }*/

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
