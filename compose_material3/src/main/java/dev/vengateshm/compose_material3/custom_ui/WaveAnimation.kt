package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun WaveAnimation(
    numWaves: Int = 6,
    color: Color = MaterialTheme.colorScheme.primary,
    width: Dp = 4.dp,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "waveTransition")
    val waves = List(numWaves) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0.3f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, delayMillis = index * 100),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "wave$index",
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(width),
        modifier = Modifier.height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        waves.forEach { wave ->
            Box(
                modifier = Modifier
                    .width(width)
                    .height(30.dp * wave.value)
                    .background(
                        color = color, shape = MaterialTheme.shapes.medium,
                    ),
            )
        }
    }
}