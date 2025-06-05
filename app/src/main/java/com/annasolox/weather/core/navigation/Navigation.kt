package com.annasolox.weather.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.annasolox.weather.ui.screens.SearchScreen
import com.annasolox.weather.ui.screens.WeatherScreen
import com.annasolox.weather.ui.viewmodel.SharedViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = hiltViewModel()

    Scaffold(
        containerColor = Color(0xFF1E88E5),
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = listOf(WeatherScreen, SearchScreen)
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = WeatherScreen,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable<WeatherScreen> {
                WeatherScreen(sharedViewModel = sharedViewModel)
            }
            composable<SearchScreen> {
                SearchScreen(
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}