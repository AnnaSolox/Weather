package com.annasolox.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.annasolox.weather.ui.model.ForecastUi

@Composable
fun ForecastItem(
    forecast: ForecastUi,
    globalMin: Float,
    totalRange: Float){

    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFF9C4),
            Color(0xFFFF6F00)
        )
    )

    val startPercent = ((forecast.minTemp - globalMin) / totalRange).toFloat().coerceAtLeast(0f)
    val endPercent = ((forecast.maxTemp - globalMin) / totalRange).toFloat().coerceIn(startPercent, 1f)
    val linearBoxTemp = 72.dp

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(.25f),
            text = forecast.date.substringBefore(","),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )

        Icon(
            painter = painterResource(forecast.icon),
            contentDescription = "Forecast Icon",
            tint = Color.White,
            modifier = Modifier.size(16.dp).weight(.4f)

        )

        Text(
            modifier = Modifier.weight(1f),
            text = forecast.description,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "${forecast.minTemp}º",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )

            Box(
                modifier = Modifier
                    .width(linearBoxTemp)
                    .height(4.dp)
                    .background(Color.LightGray, RoundedCornerShape(4.dp))
            ) {
                val totalWidth = linearBoxTemp
                val barStart = startPercent * totalWidth.value
                val barWidth = (endPercent - startPercent) * totalWidth.value

                Box(
                    modifier = Modifier
                        .offset(x = barStart.dp)
                        .width(barWidth.dp)
                        .fillMaxHeight()
                        .background(brush = gradientBrush, RoundedCornerShape(4.dp))
                )
            }

            Text(
                text = "${forecast.maxTemp}º",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }
}