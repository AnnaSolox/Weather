package com.annasolox.weather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.annasolox.weather.R

@Preview(showBackground = true)
@Composable
fun PropertiesBlock(
    humidity: Int = 60,
    wind: Double = 30.0,
    pressure: Int = 1018
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Black.copy(alpha = 0.3f))
    ) {
        Row(
            Modifier.padding(horizontal = 8.dp, vertical = 14.dp)
        ) {
            Property(
                modifier = Modifier.weight(1f),
                icon = R.drawable.drop_icon,
                contentDescription = stringResource(R.string.humidity_icon_cd),
                propertyName = stringResource(R.string.humidity_property),
                propertyValue = "${humidity}%"
            )
            Property(
                modifier = Modifier.weight(1f),
                icon = R.drawable.wind_icon,
                contentDescription = stringResource(R.string.wind_icon_cd),
                propertyName = stringResource(R.string.wind_preperty),
                propertyValue = "$wind m/s"
            )
            Property(
                modifier = Modifier.weight(1f),
                icon = R.drawable.pressure_icon,
                contentDescription = stringResource(R.string.pressure_icon_cd),
                propertyName = stringResource(R.string.pressure_property),
                propertyValue = "$pressure hPa"
            )
        }
    }
}