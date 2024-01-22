package dev.vengateshm.android_kotlin_compose_practice.custom_progress_bar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ArcProgressBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    progressColor: Color,
    strokeWidth: Float = 8f,
    startAngle: Float = 180f,
    progress: Float = 0f,
    animationDurationInMillis: Int = 1000,
) {
    val currentProgress by rememberUpdatedState(newValue = progress)

    val progressAnimatable = remember { Animatable(currentProgress) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = progress) {
        progressAnimatable.animateTo(
            targetValue = currentProgress,
            animationSpec =
                repeatable(
                    iterations = 1,
                    animation =
                        tween(
                            durationMillis = animationDurationInMillis,
                            easing = LinearEasing,
                        ),
                ),
        )
    }

    DisposableEffect(progress) {
        onDispose {
            scope.launch { progressAnimatable.stop() }
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val size = minOf(size.width, size.height)
        val arcRadius = size / 2 - strokeWidth / 2
        val center = Offset(size / 2, size / 2)

        drawArc(
            color = backgroundColor,
            startAngle = startAngle,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
        drawArc(
            color = progressColor,
            startAngle = startAngle,
            sweepAngle = progressAnimatable.value * 180f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )

        // Thumb touches top of the arc
        /*val thumbRadius = strokeWidth
        val thumbAngle = startAngle + progressAnimatable.value * 180f
        val thumbX =
            center.x + arcRadius * kotlin.math.cos(Math.toRadians(thumbAngle.toDouble())).toFloat()
        val thumbY =
            center.y + arcRadius * kotlin.math.sin(Math.toRadians(thumbAngle.toDouble())).toFloat()*/

        // Thumb touches bottom of the arc
        /*val thumbRadius = strokeWidth
        val thumbAngle = startAngle + progressAnimatable.value * 180f
        val thumbX =
            center.x + (arcRadius + strokeWidth) * kotlin.math.cos(Math.toRadians(thumbAngle.toDouble())).toFloat()
        val thumbY =
            center.y + (arcRadius + strokeWidth) * kotlin.math.sin(Math.toRadians(thumbAngle.toDouble())).toFloat()*/
        val thumbRadius = strokeWidth
        val thumbAngle = startAngle + progressAnimatable.value * 180f
        val thumbX =
            center.x + (arcRadius + strokeWidth / 2) *
                kotlin.math.cos(Math.toRadians(thumbAngle.toDouble()))
                    .toFloat()
        val thumbY =
            center.y + (arcRadius + strokeWidth / 2) *
                kotlin.math.sin(Math.toRadians(thumbAngle.toDouble()))
                    .toFloat()
        drawCircle(
            color = progressColor,
            radius = thumbRadius,
            center = Offset(thumbX, thumbY),
        )

//

        val lineAngle = startAngle + progressAnimatable.value * 180f
        val lineEndX =
            center.x + (arcRadius - strokeWidth) *
                kotlin.math.cos(Math.toRadians(lineAngle.toDouble()))
                    .toFloat()
        val lineEndY =
            center.y + (arcRadius - strokeWidth) *
                kotlin.math.sin(Math.toRadians(lineAngle.toDouble()))
                    .toFloat()

        // Draw a line from the center circle to the arc
        drawLine(
            color = progressColor,
            start = center,
            end = Offset(lineEndX, lineEndY),
            strokeWidth = strokeWidth,
        )

        // Draw a triangle needle
        /*val needleAngle = startAngle + progressAnimatable.value * 180f
        val needlePath = Path().apply {
            moveTo(center.x, center.y)
            lineTo(center.x - strokeWidth / 2, center.y)
            lineTo(
                center.x + arcRadius * kotlin.math.cos(Math.toRadians(needleAngle.toDouble()))
                    .toFloat(),
                center.y + arcRadius * kotlin.math.sin(Math.toRadians(needleAngle.toDouble()))
                    .toFloat()
            )
            lineTo(center.x + strokeWidth / 2, center.y)
            close()
        }*/
    }
}

@Preview
@Composable
fun ArcProgressBarPreview() {
    ArcProgressBar(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color(0xFFFFAB91), // Light Peach-Orange
        progressColor = Color(0xFFFF7043), // Salmon-Orange
        strokeWidth = 16f,
        progress = 0.3f,
    )
}

@Composable
fun ArcProgressBarSample() {
    var progress by remember { mutableFloatStateOf(0.0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArcProgressBar(
            modifier = Modifier.size(200.dp),
            backgroundColor = Color(0xFFFFAB91), // Light Peach-Orange
            progressColor = Color(0xFFFF7043), // Salmon-Orange
            strokeWidth = 32f,
            startAngle = 180f,
            progress = progress,
        )
        Slider(
            value = progress,
            onValueChange = { newProgress ->
                progress = newProgress
            },
            valueRange = 0f..1f,
        )
    }
}
