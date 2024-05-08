package dev.vengateshm.compose_material3.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val BarColor = Color(0xFF55466E)

@Composable
fun CanvasGridSample(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(color = Color(0xFF2E1E4C))
            .fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(3 / 2f)
                .fillMaxSize()
        ) {
            val borderWidth = 1.dp.toPx()
            drawRect(color = BarColor, style = Stroke(width = borderWidth))

            val verticalLines = 4
            val verticalSize = size.width / (verticalLines + 1)
            repeat(verticalLines) { i ->
                val startX = verticalSize * (i + 1)
                drawLine(
                    color = BarColor,
                    start = Offset(x = startX, y = 0f),
                    end = Offset(x = startX, y = size.height),
                    strokeWidth = borderWidth
                )
            }

            val horizontalLines = 4
            val horizontalSize = size.height / (horizontalLines + 1)
            repeat(horizontalLines) { i ->
                val startY = horizontalSize * (i + 1)
                drawLine(
                    color = BarColor,
                    start = Offset(x = 0f, y = startY),
                    end = Offset(x = size.width, y = startY),
                    strokeWidth = borderWidth
                )
            }
        }
    }
}

@Preview
@Composable
private fun CanvasGridSamplePreview() {
    CanvasGridSample()
}