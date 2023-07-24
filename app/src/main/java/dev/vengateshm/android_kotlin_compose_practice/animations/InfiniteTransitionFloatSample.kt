package dev.vengateshm.android_kotlin_compose_practice.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun InfiniteFloatTransition(width: Int, height: Int) {
    val transition = rememberInfiniteTransition()
    val x1 by transition.animateFloat(
        initialValue = 100f,
        targetValue = width.toFloat() - 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 7000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val y1 by transition.animateFloat(
        initialValue = 100f,
        targetValue = height.toFloat() - 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 6000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0XFF8E24AA),
                        Color(0XFF9C27B0),
                        Color(0XFFD1C4E9),
                    ),
                    start = Offset(x1 - 100, y1),
                    end = Offset(x1 + 100, y1)
                ),
                radius = 100f,
                center = Offset(x1, y1)
            )
        }
    }
}