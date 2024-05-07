package dev.vengateshm.compose_material3.canvas

import androidx.annotation.FloatRange
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.time.Duration.Companion.milliseconds

private val MAJOR_INDICATOR_LENGTH = 18.dp
private val MINOR_INDICATOR_LENGTH = 14.dp
private val INDICATOR_INITIAL_OFFSET = 5.dp

@Composable
fun Speedometer(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 240.0) currentSpeed: Float
) {
    val textMeasurer = rememberTextMeasurer()

    var newSpeed by remember {
        mutableFloatStateOf(0f)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = newSpeed,
        label = "speedometer_needle_animation",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy)
    )

    LaunchedEffect(key1 = Unit) {
        delay(5.milliseconds)
        newSpeed = currentSpeed
    }

    Canvas(
        modifier = modifier
            .padding(90.dp)
            .requiredSize(360.dp)
    ) {
        drawArc(
            color = Color.Red,
            startAngle = 30f,
            sweepAngle = -240f,
            useCenter = false,
            style = Stroke(width = 2.0.dp.toPx()),
        )

        for (angle in 300 downTo 60 step 2) {
            val speed = 300 - angle

            val startOffset = pointOnCircle(
                angleInDegrees = angle.toDouble(),
                radius = size.height / 2 - INDICATOR_INITIAL_OFFSET.toPx(),
                cx = center.x,
                cy = center.y
            )

            if (angle % 20 == 0) {
                val endOffset = pointOnCircle(
                    angleInDegrees = angle.toDouble(),
                    radius = size.height / 2 - MAJOR_INDICATOR_LENGTH.toPx(),
                    cx = center.x,
                    cy = center.y
                )
                drawLine(
                    color = Color.Black,
                    start = startOffset,
                    end = endOffset,
                    strokeWidth = 2.dp.toPx()
                )
                val textLayoutResult =
                    textMeasurer.measure(speed.toString(), style = TextStyle.Default)
                val textWidth = textLayoutResult.size.width
                val textHeight = textLayoutResult.size.height

                val textOffset = pointOnCircle(
                    angleInDegrees = angle.toDouble(),
                    radius = size.height / 2 - MAJOR_INDICATOR_LENGTH.toPx() - textWidth / 2 - INDICATOR_INITIAL_OFFSET.toPx(),
                    cx = center.x - textWidth / 2,
                    cy = center.y - textHeight / 2
                )

                drawText(
                    textLayoutResult = textLayoutResult,
                    color = Color.Black,
                    topLeft = textOffset
                )
            } else if (angle % 10 == 0) {
                val endOffset = pointOnCircle(
                    angleInDegrees = angle.toDouble(),
                    radius = size.height / 2 - MINOR_INDICATOR_LENGTH.toPx(),
                    cx = center.x,
                    cy = center.y
                )
                drawLine(
                    color = Color.Blue,
                    start = startOffset,
                    end = endOffset,
                    strokeWidth = 2.dp.toPx()
                )
            } else {
                val endOffset = pointOnCircle(
                    angleInDegrees = angle.toDouble(),
                    radius = size.height / 2 - MINOR_INDICATOR_LENGTH.toPx(),
                    cx = center.x,
                    cy = center.y
                )
                drawLine(
                    color = Color.Black,
                    start = startOffset,
                    end = endOffset,
                )
            }

            val currentSpeedOffset = pointOnCircle(
                angleInDegrees = 300 - animatedProgress.toDouble(),
                radius = size.height / 2 - MAJOR_INDICATOR_LENGTH.toPx() - 4.dp.toPx(),
                cx = center.x,
                cy = center.y
            )
            drawLine(
                color = Color.Magenta.copy(alpha = 0.7f),
                start = center,
                end = currentSpeedOffset,
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}

private fun pointOnCircle(
    angleInDegrees: Double,
    radius: Float,
    cx: Float = 0f,
    cy: Float = 0f,
): Offset {
    val x = cx + (radius * sin(Math.toRadians(angleInDegrees)).toFloat())
    val y = cy + (radius * cos(Math.toRadians(angleInDegrees)).toFloat())

    return Offset(x = x, y = y)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SpeedometerPreview() {
    MaterialTheme {
        Speedometer(currentSpeed = 45f)
    }
}