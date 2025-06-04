package com.annasolox.weather.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.annasolox.weather.core.Resource
import com.annasolox.weather.ui.components.ForecastComponent
import com.annasolox.weather.ui.components.MainInfo
import com.annasolox.weather.ui.components.PlaceAndDate
import com.annasolox.weather.ui.components.PropertiesBlock
import com.annasolox.weather.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weather by viewModel.weather.observeAsState(Resource.Loading)

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Box(modifier.fillMaxSize()) {
            when (weather) {
                is Resource.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                    {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                }

                is Resource.Error -> {
                    val error = (weather as Resource.Error).exception
                    Text("Error: ${error.message}")
                }

                is Resource.Success -> {
                    val weatherData = (weather as Resource.Success).data
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        PlaceAndDate(
                            weatherData.city,
                            weatherData.date
                        )
                        Spacer(Modifier.size(28.dp))
                        MainInfo(
                            icon = weatherData.icon,
                            temperature = weatherData.currentTemp,
                            description = weatherData.description
                        )
                        Spacer(Modifier.size(32.dp))
                        PropertiesBlock(
                            humidity = weatherData.humidity,
                            pressure = weatherData.pressure,
                            wind = weatherData.wind,
                        )
                        Spacer(Modifier.size(16.dp))
                        ForecastComponent(
                            modifier = Modifier.height(130.dp),
                            forecasts = weatherData.forecast
                        )
                    }
                }
            }
        }
    }
}