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
fun QuadLineChartWithPoints(
    modifier: Modifier = Modifier,
    dataPoints: List<Float>
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val xStep = size.width / (dataPoints.size - 1) // Divide x axis based on number of points
        val yStep = size.height / (dataPoints.maxOrNull() ?: 1f) // Divide y axis based on max value

        val linePath = Path()

        dataPoints.forEachIndexed { index, dataPoint ->
            val xPos = index * xStep
            val yPos = size.height - (dataPoint * yStep)

            if (index == 0) {
                linePath.moveTo(xPos, yPos)
            } else {
                val prevPoint = dataPoints[index - 1]
                val prevXPos = (index - 1) * xStep
                val prevYPos = size.height - (prevPoint * yStep)
                val controlX = (prevXPos + xPos) / 2f
                linePath.quadraticTo(
                    controlX, prevYPos,
                    xPos, yPos
                )
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