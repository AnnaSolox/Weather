package com.annasolox.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.annasolox.weather.domain.model.CityDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _selectedCity = MutableStateFlow<CityDomain?>(null)
    val selectedCity: StateFlow<CityDomain?> = _selectedCity

    fun selectCity(city: CityDomain) {
        _selectedCity.value = city
    }
}