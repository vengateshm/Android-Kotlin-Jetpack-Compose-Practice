package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PulsingCirclesAnimation(modifier: Modifier = Modifier) {
    val infiniteTransition =
        rememberInfiniteTransition(label = "pulsing circles infinite transition")
    val sizes = listOf(100.dp, 150.dp, 200.dp)

    val scaleFactors = List(sizes.size) { index ->
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.4f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 6000
                    0.0f at 0 using LinearEasing
                    0.2f at (1000 + (index * 1000)) using LinearEasing
                    1.2f at (2000 + (index * 1000)) using LinearEasing
                    1.4f at (4000 + (index * 1000)) using LinearEasing
                    1.6f at 6000 using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
            ),
            label = "",
        )
    }

    val alphaValues = List(sizes.size) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 6000
                    0.0f at 0 using LinearEasing
                    0.2f at (2000 + (index * 1000)) using LinearEasing
                    0.1f at (4000 + (index * 1000)) using LinearEasing
                    0.0f at 6000 using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
            ),
            label = "",
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            sizes.forEachIndexed { index, size ->
                val scale = scaleFactors[index].value
                val alpha = alphaValues[index].value
                val radius = size.toPx() / 2

                drawCircle(
                    color = Color.Red,
                    radius = radius * scale,
                    center = center,
                    alpha = alpha,
                )
            }
            drawCircle(
                color = Color.Red,
                radius = 100f,
                center = center,
            )
        }
    }
}

@Preview
@Composable
private fun PulsingCirclesAnimationPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        PulsingCirclesAnimation()
    }
}