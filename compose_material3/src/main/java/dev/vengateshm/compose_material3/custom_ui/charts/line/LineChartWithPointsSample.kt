package dev.vengateshm.compose_material3.custom_ui.charts.line

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LineChartWithPointsSample(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        LineChartWithPoints(
            points = listOf(0.5f, 0.4f, 0.6f, 0.8f, 0.4f, 0.7f, 0.2f)
        )
        Spacer(modifier = Modifier.height(40.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(40.dp))
        CubicLineChartWithPoints(
            points = listOf(0.5f, 0.4f, 0.6f, 0.8f, 0.4f, 0.7f, 0.2f)
        )
        HorizontalDivider()
        Spacer(modifier = Modifier.height(40.dp))
        QuadLineChartWithPoints(
            dataPoints = listOf(0.5f, 0.4f, 0.6f, 0.8f, 0.4f, 0.7f, 0.2f)
        )
    }
}