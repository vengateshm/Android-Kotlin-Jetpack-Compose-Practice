package dev.vengateshm.compose_material3.custom_ui.speed_test

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@Composable
fun SpeedIndicator(
    modifier: Modifier = Modifier,
    uiState: SpeedTestUiState,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.BottomCenter
    ) {
        CircularSpeedIndicator(value = uiState.arcValue, angle = 240f)
        SpeedValue(value = uiState.speed)
        StartButton(isEnabled = !uiState.inProgress, onClick = onClick)
    }
}

@Composable
fun CircularSpeedIndicator(modifier: Modifier = Modifier, value: Float, angle: Float) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        drawLines(value, angle)
        drawArcs(value, angle)
    }
}

private fun DrawScope.drawLines(progress: Float, maxValue: Float, lines: Int = 40) {
    val oneRotation = maxValue / lines
    val startValue = if (progress == 0f) 0 else floor(progress * lines).toInt()

    for (i in startValue..lines) {
        rotate(i * oneRotation + (180 - maxValue) / 2) {
            drawLine(
                color = Color(0XFFAAAAAA),
                start = Offset(x = if (i % 5 == 0) 80f else 30f, y = size.height / 2),
                end = Offset(x = 0f, y = size.height / 2),
                8f,
                StrokeCap.Round
            )
        }
    }
}

private fun DrawScope.drawArcs(progress: Float, maxValue: Float) {
    val startAngle = 270f - maxValue / 2
    val sweepAngle = maxValue * progress

    val topLeft = Offset(x = 50f, y = 50f)
    val size = Size(width = size.width - 100f, height = size.height - 100f)

    fun drawBlur() {
        for (i in 0..20) {
            drawArc(
                color = Color(0xFFA5D6A7).copy(alpha = i / 900f),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                topLeft = topLeft,
                useCenter = false,
                size = size,
                style = Stroke(width = 80f + (20 - i) * 20, cap = StrokeCap.Round)
            )
        }
    }

    fun drawStroke() {
        drawArc(
            color = Color(0xFF4CAF50),
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            topLeft = topLeft,
            useCenter = false,
            size = size,
            style = Stroke(width = 86f, cap = StrokeCap.Round)
        )
    }

    fun drawGradient() {
        drawArc(
            brush = Brush.linearGradient(listOf(Color(0xFFA5D6A7), Color(0xFF4CAF50))),
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            topLeft = topLeft,
            useCenter = false,
            size = size,
            style = Stroke(width = 80f, cap = StrokeCap.Round)
        )
    }

    drawBlur()
    drawGradient()
    drawStroke()
}

private fun DrawScope.drawSemiCircularLines() {
    val start = Offset(x = size.width / 10, y = size.height / 2)
    val end = Offset(x = 0f, y = size.height / 2)

    for ((i, angle) in ((0..180) step 6).withIndex()) {
        rotate(angle.toFloat()) {
            drawLine(
                color = Color.Black,
                start = if (i % 5 == 0) start else start.copy(x = size.width / 20),
                end = end
            )
        }
    }
}

private fun DrawScope.drawLines1(angleSize: Float, lines: Int) {
    val oneRotation = angleSize / lines
    val startingAngle = 90 - angleSize / 2
    val shortLine = size.width / 20
    val longLine = size.width / 10

    for (i in 0..lines) {
        rotate(i * oneRotation + startingAngle) {
            drawLine(
                color = Color.Black,
                start = Offset(x = if (i % 5 == 0) longLine else shortLine, y = size.height / 2),
                end = Offset(x = 4f, y = size.height / 2)
            )
        }
    }
}

private fun DrawScope.drawArc(angleSize: Float) {
    val startAngle = 270f - angleSize / 2
    drawArc(
        color = Color.LightGray,
        startAngle = startAngle,
        sweepAngle = angleSize,
        useCenter = false,
        style = Stroke(width = 30f)
    )
}

@Preview(showBackground = true)
@Composable
private fun SpeedIndicatorPreview() {
    SpeedIndicator(uiState = SpeedTestUiState(speed = "150.0"), onClick = {})
}