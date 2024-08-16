package dev.vengateshm.compose_material3.api_compose.brush

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun BrushWithInfiniteTransitionSample(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(
        label = "background"
    )
    val targetOffset = with(LocalDensity.current) {
        1000.dp.toPx()
    }

    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetOffset,
        animationSpec = infiniteRepeatable(
            animation = tween(50000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offset"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .blur(40.dp)
                .drawWithCache {
                    val brushSize = 400f
                    val brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0XFF7787f9),
                            Color(0XFF87f8fb)
                        ),
                        start = Offset(offset, offset),
                        end = Offset(offset + brushSize, offset + brushSize),
                        tileMode = TileMode.Mirror
                    )
                    onDrawBehind {
                        drawRect(brush)
                    }
                }
        )
    }
}