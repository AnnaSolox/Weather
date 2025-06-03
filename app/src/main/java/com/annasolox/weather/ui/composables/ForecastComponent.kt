package com.annasolox.weather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.annasolox.weather.ui.model.Forecast

@Composable
fun ForecastComponent(
    modifier: Modifier = Modifier,
    forecasts: List<Forecast>
) {
    val globalMin = forecasts.minOfOrNull { it.minTemp.toFloat() } ?: 0f
    val globalMax = forecasts.maxOfOrNull { it.maxTemp.toFloat() } ?: 1f
    val totalRange = globalMax - globalMin

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Black.copy(alpha = .3f))
    ) {
        LazyColumn {
            items(forecasts) { forecast ->
                ForecastItem(
                    forecast = forecast,
                    globalMin = globalMin,
                    totalRange = totalRange
                )
            }
        }
    }
}