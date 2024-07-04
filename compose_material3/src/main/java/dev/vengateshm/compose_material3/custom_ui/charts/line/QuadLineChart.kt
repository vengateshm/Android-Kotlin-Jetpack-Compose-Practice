package dev.vengateshm.compose_material3.custom_ui.charts.line

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun QuadLineChart(
    data: List<Pair<Int, Double>> = emptyList(),
    modifier: Modifier = Modifier
) {
    val startSpacingX = 100f
    val graphColor = Color.Red
    val graphFillColor = remember {
        graphColor.copy(alpha = 0.5f)
    }

    val upperValue = remember {
        (data.maxOfOrNull { it.second }?.plus(1))?.roundToInt() ?: 0
    }
    val lowerValue = remember {
        (data.minOfOrNull { it.second }?.toInt() ?: 0)
    }

    val density = LocalDensity.current

    val textPaint = remember(key1 = density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = with(density) { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {
        val availableWidth = size.width - startSpacingX
        val spacePerHour = availableWidth / data.size
        (data.indices step 2).forEach { i ->
            val hour = data[i].first
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hour.toString(),
                    startSpacingX + i * spacePerHour,
                    size.height,
                    textPaint
                )
            }
        }

        val priceStepY = (upperValue - lowerValue) / 5f
        (0..4).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    round(lowerValue + priceStepY * i).toString(),
                    30f,
                    size.height - startSpacingX - i * (size.height / 5f),
                    textPaint
                )
            }
        }

        var medX: Float
        var medY: Float
        val strokePath = Path().apply {
            val height = size.height
            data.indices.forEach { i ->
                val nextInfo = data.getOrNull(i + 1) ?: data.last()
                val firstRatio = (data[i].second - lowerValue) / (upperValue - lowerValue)
                val secondRatio = (nextInfo.second - lowerValue) / (upperValue - lowerValue)

                val x1 = startSpacingX + i * spacePerHour
                val y1 = height - startSpacingX - (firstRatio * height).toFloat()
                val x2 = startSpacingX + (i + 1) * spacePerHour
                val y2 = height - startSpacingX - (secondRatio * height).toFloat()

                if (i == 0) moveTo(x1, y1)
                medX = (x1 + x2) / 2f
                medY = (y1 + y2) / 2f
                quadraticBezierTo(x1 = x1, y1 = y1, x2 = medX, y2 = medY)
            }
        }

        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 2.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        val fillPath = android.graphics.Path(strokePath.asAndroidPath()).asComposePath().apply {
            lineTo(size.width - startSpacingX, size.height - startSpacingX)
            lineTo(startSpacingX, size.height - startSpacingX)
            close()
        }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    graphFillColor,
                    Color.Transparent
                ),
                endY = size.height - startSpacingX
            )
        )
    }
}

@Preview
@Composable
private fun QuadLineChartPreview() {
    QuadLineChart()
}