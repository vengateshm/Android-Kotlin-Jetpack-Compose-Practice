package dev.vengateshm.compose_material3.canvas

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(
    data: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier,
) {
    val textMeasurer = rememberTextMeasurer()
    Box(
        modifier = modifier.padding(12.dp),
        contentAlignment = Alignment.Center,
    ) {
        val total = data.sum()
        var startAngle = -90f
        Canvas(modifier = Modifier.fillMaxSize()) {
            val size = size.minDimension
            val radius = size.div(2)
            val center = Offset(size.div(2), size.div(2))

            data.forEachIndexed { index, value ->
                val sweepAngle = value.div(total).times(360f)
                drawArc(
                    color = colors[index],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2),
                )
                startAngle += sweepAngle
            }

            drawCircle(
                color = Color.White,
                center = center,
                radius = 120f,
            )

            val measure = textMeasurer.measure("Pie Chart")
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "Pie Chart",
                    center.x - (measure.size.width/2f),
                    center.y,
                    Paint().apply {
                        textSize = 24f
                        color = android.graphics.Color.BLACK
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PieChartPreview() {
    PieChart(
        data = listOf(12f, 4f, 6f, 9f),
        colors = listOf(Color.Blue, Color.Red, Color.Green, Color.Yellow),
        modifier = Modifier
            .width(300.dp)
            .height(200.dp),
    )
}