package dev.vengateshm.compose_material3.custom_ui.speed_test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SpeedValue(
    modifier: Modifier = Modifier,
    value: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "DOWNLOAD", style = MaterialTheme.typography.bodyMedium,
            color = Color(0XFFAAAAAA)
        )
        Text(
            text = value,
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "mbps", style = MaterialTheme.typography.bodyMedium,
            color = Color(0XFFAAAAAA)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SpeedValuePreview() {
    SpeedValue(value = "120.5")
}