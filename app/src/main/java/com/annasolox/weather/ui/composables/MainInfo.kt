package com.annasolox.weather.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.annasolox.weather.R

@Composable
fun MainInfo(
    icon: Int = R.drawable.sun_icon,
    temperature: Int = 23,
    description: String = "Clear Sky"
){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "Weather Icon",
            tint = Color.White,
            modifier = Modifier.size(120.dp)
        )
        Spacer(Modifier.size(16.dp))
        Row(verticalAlignment = Alignment.Top) {
            Text(
                text = "${temperature}º",
                style = MaterialTheme.typography.displayLarge,
                fontSize = 72.sp,
                color = Color.White,
                fontWeight = FontWeight.W500
            )
        }
        Text(
            text = description,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
    }
}