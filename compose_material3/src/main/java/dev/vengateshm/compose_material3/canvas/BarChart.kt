package dev.vengateshm.compose_material3.canvas

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed

@Composable
fun BarChart(
    xValues: List<Int>,
    yValues: List<Int>,
    points: List<Float>,
    interval: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier.size(300.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
        ) {
            val maxX = xValues.max()
            val xSpacing = size.width.div(maxX)

            val maxY = yValues.max()
            val ySpacing = size.height.div(maxY)

            val textPaint = Paint().apply {
                isAntiAlias = true
                textSize = 24f
                color = Color.Black.toArgb()
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

            points.fastForEachIndexed { index, value ->
                val x = xSpacing.times(index)
                val y = size.height - (ySpacing.times(value))

                drawLine(
                    color = Color.Black,
                    start = Offset(x, size.height),
                    end = Offset(x, y),
                    strokeWidth = 5f,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BarChartPreview() {
    BarChart(
        xValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
        yValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
        points = listOf(0f, 60f, 60f, 50f, 10f),
        interval = 10,
    )
}