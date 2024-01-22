package dev.vengateshm.android_kotlin_compose_practice.device_sensors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SensorData() {
    val viewModel = viewModel<SensorDataViewModel>()
    val isDark = viewModel.isDark
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    color = if (isDark) Color.DarkGray else Color.White,
                ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            color = if (isDark) Color.White else Color.DarkGray,
            text = if (isDark) "Devil dark" else "Angel White",
        )
    }
}
