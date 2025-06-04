package com.annasolox.weather.core.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.annasolox.weather.R

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    items: List<Any>
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
            .height(120.dp),
        containerColor = Color(0xFF1E88E5),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 21.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                IconButton({
                    navController.navigate(item)
                }) {
                    Icon(
                        painter =
                        when(item){
                            is WeatherScreen -> painterResource(R.drawable.sun_icon)
                            is SearchScreen -> painterResource(R.drawable.location_search_icon)
                            else -> painterResource(R.drawable.sun_icon)
                        },
                        contentDescription = "$item icon",
                        tint =
                            if(currentDestination?.route == item::class.qualifiedName)
                        Color.White else Color.Black.copy(alpha = .3f),
                        modifier = Modifier.size(27.dp)
                    )
                }
                Spacer(Modifier.size(21.dp))
            }
        }
    }
}