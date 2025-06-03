package com.annasolox.weather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annasolox.weather.core.Resource
import com.annasolox.weather.data.bbdd.model.City
import com.annasolox.weather.data.repository.WeatherRepository
import com.annasolox.weather.ui.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    private val _cities = MutableStateFlow<Resource<List<City>>>(Resource.Loading)
    val cities: StateFlow<Resource<List<City>>> = _cities

    private val _weather = MutableLiveData<Resource<Weather>>(Resource.Loading)
    val weather: LiveData<Resource<Weather>> = _weather

    fun searchCities(name: String, state: String?, country: String?){
        viewModelScope.launch {
            repository.getCitiesByName(name, state, country)
                .collect { _cities.value = it }
        }
    }

    fun getWeather(lat: Double, lon: Double, language: String){
        viewModelScope.launch {
            _weather.value = Resource.Loading
            _weather.value = repository.getWeather(lat, lon, language)
        }
    }
}