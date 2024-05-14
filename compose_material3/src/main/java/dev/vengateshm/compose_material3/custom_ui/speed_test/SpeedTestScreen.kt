package dev.vengateshm.compose_material3.custom_ui.speed_test

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.vengateshm.compose_material3.theme.Material3AppTheme
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun SpeedTestScreen(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()

    val animation = remember { Animatable(0f) }
    val maxSpeed = remember { mutableStateOf(0f) }
    maxSpeed.value = max(maxSpeed.value, animation.value * 100f)

    SpeedTestScreen(
        uiState = animation.toSpeedTestUiState(maxSpeed.value),
        onClick = {
            coroutineScope.launch {
                maxSpeed.value = 0f
                startAnimation(animation)
            }
        })
}

fun Animatable<Float, AnimationVector1D>.toSpeedTestUiState(maxSpeed: Float) = SpeedTestUiState(
    arcValue = value,
    speed = "%.1f".format(value * 100),
    ping = if (value > 0.2f) "${(value * 15).roundToInt()} ms" else "-",
    max = if (maxSpeed > 0f) "%.1f mbps".format(maxSpeed) else "-",
    inProgress = isRunning
)

suspend fun startAnimation(animation: Animatable<Float, AnimationVector1D>) {
    animation.animateTo(0.84f, keyframes {
        durationMillis = 9000
        0f at 0 using CubicBezierEasing(0f, 1.5f, 0.8f, 1f)
        0.72f at 1000 using CubicBezierEasing(0.2f, -1.5f, 0f, 1f)
        0.76f at 2000 using CubicBezierEasing(0.2f, -2f, 0f, 1f)
        0.78f at 3000 using CubicBezierEasing(0.2f, -1.5f, 0f, 1f)
        0.82f at 4000 using CubicBezierEasing(0.2f, -2f, 0f, 1f)
        0.85f at 5000 using CubicBezierEasing(0.2f, -2f, 0f, 1f)
        0.89f at 6000 using CubicBezierEasing(0.2f, -1.2f, 0f, 1f)
        0.82f at 7500 using LinearOutSlowInEasing
    })
}

@Composable
fun SpeedTestScreen(
    modifier: Modifier = Modifier,
    uiState: SpeedTestUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF121613)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header()
        SpeedIndicator(uiState = uiState, onClick = onClick)
        AdditionalInfo(ping = uiState.ping, maxSpeed = uiState.max)
    }
}

@Preview
@Composable
private fun SpeedTestScreenPreview() {
    Material3AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SpeedTestScreen(
                uiState = SpeedTestUiState(
                    ping = "5ms",
                    max = "150.0 Mbps"
                ),
                onClick = {}
            )
        }
    }
}