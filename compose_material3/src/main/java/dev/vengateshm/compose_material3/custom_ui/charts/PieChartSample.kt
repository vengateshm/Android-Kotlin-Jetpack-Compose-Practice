package dev.vengateshm.compose_material3.custom_ui.charts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class Score(
    val subject: String,
    val score: Int
)

data class PieChartData(
    val title: String,
    val point: Int,
    val color: Color = Color.random()
)

private fun Color.Companion.random(): Color {
    return Color(Random.nextLong(0XFFFFFFFF))
}

@Composable
fun PieChartSample(modifier: Modifier = Modifier) {
    val scores = listOf(
        Score(subject = "English", score = 80),
        Score(subject = "Science", score = 90),
        Score(subject = "Mathematics", score = 97),
        Score(subject = "Computer Science", score = 100),
        Score(subject = "Economics", score = 53),
    )
    PieChart(modifier = modifier, chartDataPoints = scores.map {
        PieChartData(
            title = it.subject,
            point = it.score,
        )
    })
}

@Composable
fun PieChart(modifier: Modifier = Modifier, chartDataPoints: List<PieChartData>) {
    val animateSize = remember {
        Animatable(initialValue = 0.dp.value)
    }
    val animateAngle = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = Unit) {
        animateSize.animateTo(400.dp.value, tween(1000))
        animateAngle.animateTo(360f, tween(1000))
    }

    val total = chartDataPoints.sumOf { it.point }
    Box(
        modifier = modifier
            .size(animateSize.value.dp)
    ) {
        Canvas(
            modifier = modifier
                .size(animateSize.value.dp)
                .graphicsLayer {
                    rotationZ = animateAngle.value
                }
        ) {
            /*var startAngle = 270f
            chartDataPoints.forEachIndexed { index, pieChartData ->
                val sweepAngle = 360f * (pieChartData.point.toFloat() / total)
                drawPie(
                    color = pieChartData.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                )
                startAngle += sweepAngle
            }*/
            chartDataPoints.forEachIndexed { index, pieChartData ->
                val startAngle = 360f * chartDataPoints.take(index).sumOf { it.point } / total
                val sweepAngle = 360f * pieChartData.point.toFloat() / total
                /*drawPie(
                    color = pieChartData.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                )*/
                drawRing(
                    color = pieChartData.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                )
            }
        }
    }
}

fun DrawScope.drawRing(
    color: Color,
    startAngle: Float,
    sweepAngle: Float
) {
    val padding = 100f
    this.drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(width = size.width - padding, height = size.height - padding),
        style = Stroke(width = 20f, cap = StrokeCap.Butt),
        topLeft = Offset(x = padding / 2f, y = padding / 2f)
    )
}

fun DrawScope.drawPie(
    color: Color,
    startAngle: Float,
    sweepAngle: Float
) {
    val padding = 100f
    this.drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = true,
        size = Size(width = size.width - padding, height = size.height - padding),
        topLeft = Offset(x = padding / 2f, y = padding / 2f)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PieChartSamplePreview() {
    PieChartSample(
        modifier = Modifier.size(200.dp)
    )
}