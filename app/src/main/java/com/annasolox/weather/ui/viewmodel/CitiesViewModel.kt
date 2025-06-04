package com.annasolox.weather.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annasolox.weather.domain.GetCitiesUseCase
import com.annasolox.weather.domain.GetCityByIdUseCase
import com.annasolox.weather.domain.model.CityDomain
import com.annasolox.weather.mapper.CityMapper
import com.annasolox.weather.ui.model.CityUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getCityByIdUseCase: GetCityByIdUseCase
): ViewModel() {
    private val _cities = MutableStateFlow(listOf<CityUi>())
    val cities: StateFlow<List<CityUi>> = _cities

    private val _selectedCity = MutableStateFlow<CityDomain?>(null)
    val selectedCity: StateFlow<CityDomain?> = _selectedCity

    fun searchCities(
        query: String,
    ) {
        viewModelScope.launch {
            val parts = query.split(",").map { it.trim() }
            val name = parts.getOrNull(0) ?: ""
            if(name.isBlank()) {
                _cities.value = emptyList()
                return@launch
            }
            val country = parts.getOrNull(1) ?: ""
            Log.d("CitiesViewModel", "Searching cities with: name='$name', country='$country'")
            getCitiesUseCase(name, country)
                .map { list -> list.map { CityMapper.fromDomainToUi(it) } }
                .collect { _cities.value = it }
        }
    }

    fun getCityById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val city = getCityByIdUseCase(id)
            _selectedCity.value = city
        }
    }
}