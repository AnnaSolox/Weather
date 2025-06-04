package com.annasolox.weather.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PlaceAndDate(
    place: String = "Barcelona",
    date: String = "Tuesday, May 3, 2025"
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = place,
            style = MaterialTheme.typography.displayMedium,
            color = Color.White,
            fontWeight = FontWeight.W500
        )
        Text(
            text = date,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}