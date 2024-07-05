package dev.vengateshm.compose_material3.api_compose.custom_modifiers

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedCircleBorderSample(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .drawAnimatedCircleBorder(
                    strokeWidth = 16.dp,
                    durationMillis = 1500,
                    shape = RoundedCornerShape(percent = 100)
                ),
            onClick = {

            }) {
            Text(text = "Check In")
        }
    }
}

fun Modifier.drawAnimatedCircleBorder(
    strokeWidth: Dp,
    durationMillis: Int,
    shape: Shape,
    brush: (Size) -> Brush = {
        Brush.sweepGradient(listOf(Color.Blue, Color.Red, Color.Yellow, Color.Green))
    }
): Modifier = composed {

    val infiniteTransition = rememberInfiniteTransition(label = "rotation transition")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing)
        ),
        label = "rotation animation"
    )

    Modifier
        .clip(shape = shape)
        .drawWithCache {
            val strokeWidthInPx = strokeWidth.toPx()
            val outline = shape.createOutline(size, layoutDirection, this)
            onDrawWithContent {
                drawContent()
                with(drawContext.canvas.nativeCanvas) {
                    val checkPoint = saveLayer(null, null)
                    drawOutline(
                        outline = outline,
                        color = Color.Gray,
                        style = Stroke(width = strokeWidthInPx * 2)
                    )

                    rotate(angle) {
                        drawCircle(
                            brush = brush(size),
                            radius = size.width,
                            blendMode = BlendMode.SrcIn
                        )
                    }

                    restoreToCount(checkPoint)
                }
            }
        }
}