package com.annasolox.weather.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.annasolox.weather.R
import com.annasolox.weather.core.navigation.WeatherScreen
import com.annasolox.weather.ui.components.CityItem
import com.annasolox.weather.ui.viewmodel.CitiesViewModel
import com.annasolox.weather.ui.viewmodel.SharedViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: CitiesViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
    val cities by viewModel.cities.collectAsState()
    var query by rememberSaveable { mutableStateOf("") }

    val selectedCity by viewModel.selectedCity.collectAsState()
    Log.d("SearchScreen", "Selected city: $selectedCity")

    val selectedCityShared by sharedViewModel.selectedCity.collectAsState()
    Log.d("SearchScreen", "Selected city from shared view model: $selectedCityShared")

    LaunchedEffect(selectedCity) {
        selectedCity?.let {
            sharedViewModel.selectCity(it)
            navController.navigate(WeatherScreen){
                popUpTo(WeatherScreen){inclusive = true}
                launchSingleTop = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.5f),
                cursorColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White.copy(alpha = 0.5f)
            ),
            value = query,
            onValueChange = {
                query = it
                Log.d("SearchScreen", "Search query: $query")
                viewModel.searchCities(query)
            },
            singleLine = true,
            label = { Text(stringResource(R.string.search_city_label)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.location_search_icon),
                    contentDescription = stringResource(R.string.search_icon_cd),
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items (cities) { city ->
                CityItem(city = city){
                    viewModel.getCityById(city.id)
                }
            }
        }
    }
}