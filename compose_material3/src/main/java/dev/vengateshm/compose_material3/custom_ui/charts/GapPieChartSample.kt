package dev.vengateshm.compose_material3.custom_ui.charts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.theme.Material3AppTheme
import kotlinx.coroutines.launch

@Composable
fun GapPieChart(
    modifier: Modifier = Modifier,
    dataPoints: List<NPieData>,
    content: @Composable (modifier: Modifier) -> Unit
) {
    val localModifier = modifier
        .size(200.dp)
        .padding(16.dp)

    val gapDegrees = 20
    val numOfGaps = dataPoints.size
    val remainingDegrees = 360 - (gapDegrees * numOfGaps)
    val total = (dataPoints.fold(0f) { acc, pieData -> acc + pieData.value }).div(remainingDegrees)

    var currentSum = 0f
    val arcs = dataPoints.mapIndexed { index, dataPoint ->
        val startAngle = currentSum + (index * gapDegrees)
        currentSum += dataPoint.value / total
        ArcData(
            animation = Animatable(0f),
            startAngle = -90 + startAngle,
            targetSweepAngle = dataPoint.value / total,
            color = dataPoint.color
        )
    }

    LaunchedEffect(key1 = arcs) {
        arcs.mapIndexed { index, arcData ->
            launch {
                arcData.animation.animateTo(
                    targetValue = arcData.targetSweepAngle,
                    animationSpec = tween(
                        durationMillis = 2000,
                        easing = LinearEasing,
                        delayMillis = index * 2000
                    )
                )
            }
        }
    }

    Canvas(modifier = localModifier) {
        val stroke = Stroke(width = 50f, cap = StrokeCap.Round)

        arcs.reversed().map {
            drawArc(
                startAngle = it.startAngle,
                sweepAngle = it.animation.value,
                color = it.color,
                useCenter = false,
                style = stroke
            )
        }
    }

    content(localModifier)
}

@Composable
fun GapPieChartSample(modifier: Modifier = Modifier) {
    val dataPoints = listOf(
        NPieData(value = 20, color = Color(0xFF9400D3)),
        NPieData(value = 10, color = Color(0xFF42A1D5)),
        NPieData(value = 10, color = Color(0xFFFFFF00)),
        NPieData(value = 10, color = Color(0xFFFF7F00)),
    )
    Material3AppTheme {
        Surface {
            GapPieChart(
                modifier = Modifier
                    .padding(32.dp)
                    .aspectRatio(1f),
                dataPoints = dataPoints,
            ) {
            }
        }
    }
}

@Preview
@Composable
private fun GapPieChartSamplePreview() {
    GapPieChartSample()
}