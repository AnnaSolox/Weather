package com.annasolox.weather.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.annasolox.weather.ui.model.CityUi

@Composable
fun CityItem(
    city: CityUi,
    onCityClick: () -> Unit,
) {
    Text(
        text = city.completeName,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(onClick = {onCityClick() }),
        style = MaterialTheme.typography.bodyLarge,
        color = Color.White
    )
}