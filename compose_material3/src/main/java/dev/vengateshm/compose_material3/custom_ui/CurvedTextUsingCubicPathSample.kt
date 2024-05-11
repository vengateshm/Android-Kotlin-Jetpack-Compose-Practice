package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CurvedTextUsingCubicPathSample(modifier: Modifier = Modifier) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val radius = size.minDimension / 2
        val padding = 20
        drawCircle(
            color = Color.Black, radius = radius - padding,
            style = Stroke(width = 5f)
        )

        val c1 = Offset(
            x = radius / 2,
            y = center.y - radius
        )
        val c2 = Offset(
            x = size.width - radius / 2,
            y = center.y - radius
        )

        val path = Path().apply {
            moveTo(padding.toFloat() * 6, center.y)
            cubicTo(
                x1 = c1.x, y1 = c1.y,
                x2 = c2.x, y2 = c2.y,
                x3 = size.width - padding * 6, y3 = center.y
            )
        }

        drawContext.canvas.nativeCanvas.apply {
            drawTextOnPath(
                "😀Kotlin Is Fun!",
                path.asAndroidPath(),
                0f,
                0f,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 24.dp.toPx()
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun CurvedTextUsingCubicPathSamplePreview() {
    CurvedTextUsingCubicPathSample()
}