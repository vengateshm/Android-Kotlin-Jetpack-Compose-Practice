package dev.vengateshm.compose_material3.custom_ui.charts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.theme.Material3AppTheme
import kotlinx.coroutines.launch

data class NPieData(val title: String, val value: Int, val color: Color)

data class ArcData(
    val animation: Animatable<Float, AnimationVector1D>,
    val targetSweepAngle: Float,
    val color: Color
)

@Composable
fun NPieChart(
    modifier: Modifier = Modifier,
    dataPoints: List<NPieData>,
    content: @Composable (modifier: Modifier) -> Unit
) {
    val localModifier = modifier
        .size(200.dp)
        .padding(16.dp)
    val total = dataPoints.fold(0f) { acc, pieData -> acc + pieData.value }.div(360)
    var currentSum = 0
    val arcs = dataPoints.map {
        currentSum += it.value
        ArcData(
            animation = Animatable(0f),
            targetSweepAngle = currentSum / total,
            color = it.color
        )
    }

    LaunchedEffect(key1 = arcs) {
        arcs.mapIndexed { index, arcData ->
            launch {
                arcData.animation.animateTo(
                    targetValue = arcData.targetSweepAngle,
                    animationSpec = tween(durationMillis = (index + 1) * 2000)
                )
            }
        }
    }

    Canvas(modifier = localModifier) {
        val stroke = Stroke(width = 20f, cap = StrokeCap.Round)

        arcs.reversed().map {
            drawArc(
                startAngle = -90f,
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
fun NPieChartSample(modifier: Modifier = Modifier) {
    val dataPoints = listOf(
        NPieData("Win", 20, Color.Black),
        NPieData("Draw", 10, Color.Black.copy(alpha = 0.5f)),
        NPieData("Loss", 10, Color.LightGray),
    )
    Material3AppTheme {
        Surface {
            NPieChart(
                modifier = Modifier.padding(32.dp).aspectRatio(1f),
                dataPoints = dataPoints,
            ) {
                Row(
                    modifier = it
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    dataPoints.map {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = it.title, style = MaterialTheme.typography.bodySmall)
                            Text(
                                text = it.value.toString(),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun NPieChartSamplePreview() {
    NPieChartSample()
}