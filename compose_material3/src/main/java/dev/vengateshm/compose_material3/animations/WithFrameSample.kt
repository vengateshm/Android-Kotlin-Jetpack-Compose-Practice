package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.withFrameMillis
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun WithFrameNanosSample(modifier: Modifier = Modifier) {

    val yPosition = remember { Animatable(0f) }
    val screenHeight = LocalDensity.current.run {
        LocalConfiguration.current.screenHeightDp.dp.toPx()
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        var lastFrameTime = 0L
        while (true) {
            withFrameNanos { frameTime ->
                if (lastFrameTime != 0L) {
                    val deltaSec = (frameTime - lastFrameTime) / 1_000_000_000f
                    val newYPosition = yPosition.value + 200f * deltaSec
                    scope.launch { yPosition.snapTo(if (newYPosition > screenHeight) 0f else newYPosition) }
                }
                lastFrameTime = frameTime
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFFFFC400),
                radius = 30f,
                center = Offset(x = size.width / 3, y = yPosition.value + 30f),
            )
        }
    }
}

@Preview
@Composable
fun WithFrameMillisSample(modifier: Modifier = Modifier) {
    val yPosition = remember { Animatable(0f) }
    val screenHeight = LocalDensity.current.run {
        LocalConfiguration.current.screenHeightDp.dp.toPx()
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        var lastFrameTime = 0L
        while (true) {
            withFrameMillis { frameTime ->
                if (lastFrameTime != 0L) {
                    val deltaSec = (frameTime - lastFrameTime) / 1_000f
                    val newYPosition = yPosition.value + 200f * deltaSec
                    scope.launch { yPosition.snapTo(if (newYPosition > screenHeight) 0f else newYPosition) }
                }
                lastFrameTime = frameTime
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFFFFC400),
                radius = 30f,
                center = Offset(x = size.width / 3, y = yPosition.value + 30f),
            )
        }
    }
}