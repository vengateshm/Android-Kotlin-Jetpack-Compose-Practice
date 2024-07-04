package dev.vengateshm.compose_material3.custom_ui.charts.line

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun LineChartWithPoints(
    modifier: Modifier = Modifier,
    points: List<Float>
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val xStep = size.width / (points.size - 1) // Divide x axis based on number of points
        val yStep = size.height / (points.maxOrNull() ?: 1f) // Divide y axis based on max value

        val linePath = Path()

        points.forEachIndexed { index, point ->
            val x = index * xStep
            val y = size.height - (point * yStep)

            if (index == 0) {
                linePath.moveTo(x, y)
            } else {
                linePath.lineTo(x, y)
            }
        }

        drawPath(
            path = linePath,
            color = Color.Red,
            alpha = 0.5f,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}