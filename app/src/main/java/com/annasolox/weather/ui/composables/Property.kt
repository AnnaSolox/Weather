package com.annasolox.weather.ui.composables

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.annasolox.weather.R

@Preview
@Composable
fun Property(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.drop_icon,
    contentDescription: String = "Humidity Icon",
    propertyName: String = "Humidity",
    propertyValue: String = "75%"
){
    Column(
        modifier = modifier
        ,horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.size(25.dp)
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = propertyName,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
        Spacer(Modifier.size(4.dp))
        Text(
            text = propertyValue,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}