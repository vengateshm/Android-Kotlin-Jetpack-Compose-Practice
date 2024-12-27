package dev.vengateshm.compose_material3.canvas

import android.graphics.Paint
import android.graphics.PathMeasure
import android.graphics.PointF
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed

@Composable
fun BezierCurve(
    xValues: List<Int>,
    yValues: List<Int>,
    points: List<Float>,
    interval: Int,
    modifier: Modifier = Modifier,
) {
    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(true) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000),
        )
    }
    Box(modifier = Modifier.size(300.dp)) {
        Canvas(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
        ) {
            val maxX = xValues.max()
            val xSpacing = size.width.div(maxX)
            val maxY = yValues.max()
            val ySpacing = size.height.div(maxY)

            val textPaint = Paint().apply {
                isAntiAlias = true
                textSize = 24f
                color = android.graphics.Color.BLACK
            }

            for (i in 0..maxX step interval) {
                drawContext.canvas.nativeCanvas.drawText(
                    if (i == 0) "" else i.toString(),
                    xSpacing.times(i),
                    size.height,
                    textPaint,
                )
            }

            for (i in 0..maxY step interval) {
                drawContext.canvas.nativeCanvas.drawText(
                    if (i == 0) "" else i.toString(),
                    0f,
                    size.height - (ySpacing.times(i)),
                    textPaint,
                )
            }

            val coordinates = mutableListOf<PointF>()
            points.fastForEachIndexed { index, value ->
                val x = xSpacing.times(xValues[index])
                val y = size.height - (ySpacing.times(value))
                coordinates.add(PointF(x, y))
                drawCircle(
                    Color.Blue,
                    radius = 10f,
                    center = Offset(x, y),
                )
            }

            val controlPoints = calculateControlPoints(coordinates)

            val path = Path().apply {
                reset()
                moveTo(
                    coordinates.first().x,
                    coordinates.first().y,
                )
                for (point in 1 until coordinates.size) {
                    val controlPoint = controlPoints[point - 1]
                    cubicTo(
                        x1 = controlPoint.first.x,
                        y1 = controlPoint.first.y,
                        x3 = coordinates[point].x,
                        y3 = coordinates[point].y,
                        x2 = controlPoint.second.x,
                        y2 = controlPoint.second.y,
                    )

                }
            }
            val pathMeasure = PathMeasure(path.asAndroidPath(), false)
            val animationPath = android.graphics.Path()

            pathMeasure.getSegment(
                0f,
                pathMeasure.length * animationProgress.value,
                animationPath,
                true,
            )

            drawPath(
                Path().apply { addPath(animationPath.asComposePath()) },
                color = Color.Red,
                style = Stroke(width = 12f),
            )
        }
    }
}

fun calculateControlPoints(coordinates: MutableList<PointF>): List<Pair<PointF, PointF>> {
    val controlPoints = mutableListOf<Pair<PointF, PointF>>()
    for (i in 1 until coordinates.size) {
        val previous = coordinates[i - 1]
        val current = coordinates[i]
        val next = coordinates.getOrNull(i + 1)

        val c1 = PointF(
            previous.x + (current.x - previous.x).div(3f),
            previous.y + (current.y - previous.y).div(3f),
        )
        val c2 = next?.let {
            PointF(
                current.x - (next.x - previous.x).div(3f),
                current.y - (next.y - previous.y).div(3f),
            )
        } ?: run {
            PointF(
                previous.x - (current.x - previous.x).div(3f),
                previous.y - (current.y - previous.y).div(3f),
            )
        }
        controlPoints.add(Pair(c1, c2))
    }
    return controlPoints
}

@Preview(showBackground = true)
@Composable
private fun BezierCurvePreview() {
    BezierCurve(
        modifier = Modifier.size(300.dp),
        xValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
        yValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
        interval = 20,
        points = listOf(
            0f,
            5.4f,
            2f,
            6f,
            9f,
            4f,
            2f,
            4f,
            8f,
            1f,
            11f,
            3f,
        ).map { it.times(10f) },
    )
}