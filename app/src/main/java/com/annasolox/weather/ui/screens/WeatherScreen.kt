package com.annasolox.weather.ui.screens

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.annasolox.weather.core.Resource
import com.annasolox.weather.ui.components.ForecastComponent
import com.annasolox.weather.ui.components.MainInfo
import com.annasolox.weather.ui.components.PlaceAndDate
import com.annasolox.weather.ui.components.PropertiesBlock
import com.annasolox.weather.ui.viewmodel.SharedViewModel
import com.annasolox.weather.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
    val weather by weatherViewModel.weather.observeAsState(Resource.Loading)
    val selectedCity by sharedViewModel.selectedCity.collectAsState(null)
    Log.d("WeatherScreen", "Selected city: $selectedCity")

    var actualCoordinates by rememberSaveable { mutableStateOf<Pair<Double, Double>?>(null) }

    LaunchedEffect(selectedCity) {
        val (lat, lon) = selectedCity?.let { it.lat to it.lon } ?: (39.9333 to -0.1)

        if (actualCoordinates == null || lat != actualCoordinates!!.first || lon != actualCoordinates!!.second) {
            weatherViewModel.getWeather(lat, lon)
            actualCoordinates = lat to lon
        }
    }


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

                    ConstraintLayout(Modifier.fillMaxSize()) {
                        val (placeAndDate, mainInfo, propertiesBlock) = createRefs()
                        Box(modifier = Modifier.constrainAs(placeAndDate) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                            PlaceAndDate(
                                weatherData.city,
                                weatherData.date
                            )
                        }

                        Box(modifier = Modifier.constrainAs(mainInfo) {
                            top.linkTo(placeAndDate.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                            MainInfo(
                                icon = weatherData.icon,
                                temperature = weatherData.currentTemp,
                                description = weatherData.description
                            )
                        }

                        Box(modifier = Modifier.constrainAs(propertiesBlock) {
                            top.linkTo(mainInfo.bottom, margin = 32.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }) {
                            Column(Modifier.fillMaxWidth()) {
                                PropertiesBlock(
                                    humidity = weatherData.humidity,
                                    pressure = weatherData.pressure,
                                    wind = weatherData.wind,
                                )
                                Spacer(Modifier.size(12.dp))
                                ForecastComponent(
                                    modifier = Modifier.height(155.dp),
                                    forecasts = weatherData.forecast
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}