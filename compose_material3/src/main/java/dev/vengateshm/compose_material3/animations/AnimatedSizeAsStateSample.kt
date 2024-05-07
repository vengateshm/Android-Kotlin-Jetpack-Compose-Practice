package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedSizeAsStateSample() {
    var animate by remember {
        mutableStateOf(false)
    }
    val density = LocalDensity.current
    val animateSize by animateSizeAsState(
        targetValue = with(density) {
            if (animate) Size(width = 100.dp.toPx(), height = 100.dp.toPx()) else Size(
                width = 50.dp.toPx(),
                height = 50.dp.toPx()
            )
        },
        animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
        label = ""
    )

    LaunchedEffect(Unit) {
        delay(3000L)
        animate = true
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRoundRect(color = Color.Magenta, size = animateSize)
    }
}