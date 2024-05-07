package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBorder(
    modifier: Modifier = Modifier,
    gradientColors: List<Color>,
    content: @Composable () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition(label = "animated_border_transition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 4000,
                easing = LinearEasing
            )
        ),
        label = "animated_border_animation"
    )

    val brush = if (gradientColors.isEmpty()) {
        Brush.sweepGradient(
            colors = listOf(Color.Red, Color.Blue, Color.Cyan, Color.Yellow)
        )
    } else Brush.sweepGradient(
        colors = gradientColors
    )

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .drawWithContent {
                    rotate(rotation) {
                        drawCircle(
                            brush = brush,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn
                        )
                    }
                    drawContent()
                },
            shape = RoundedCornerShape(24.dp)
        ) {
            Box(modifier = Modifier) {
                content()
            }
        }
    }
}

@Composable
fun AnimatedBorderSample() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedBorder(
            modifier = Modifier.fillMaxWidth(),
            gradientColors = emptyList()
        ) {
            Text(text = "Text with animated border")
        }
    }
}

@Preview
@Composable
private fun AnimatedBorderSamplePreview() {
    AnimatedBorderSample()
}