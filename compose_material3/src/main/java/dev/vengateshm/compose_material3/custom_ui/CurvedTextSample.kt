package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CurvedTextSample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = 8f
        val semiCircleRadius = size.minDimension / 2 - strokeWidth / 2
        val centerX = size.width / 2
        val centerY = size.height / 2

        drawCircle(color = Color.Black, radius = semiCircleRadius, style = Stroke(width = 2f))

        val path = Path()
        val factor = 24.dp.toPx()
        path.moveTo(centerX - semiCircleRadius + factor, centerY)
        path.arcTo(
            rect = Rect(
                topLeft = Offset(
                    centerX - semiCircleRadius + factor,
                    centerY - semiCircleRadius + factor
                ),
                bottomRight = Offset(
                    centerX + semiCircleRadius - factor,
                    centerY + semiCircleRadius - factor
                )
            ),
            startAngleDegrees = 180f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )

        drawContext.canvas.nativeCanvas.apply {
            drawTextOnPath(
                "Jetpack Compose",
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
fun CurvedTextSamplePreview(modifier: Modifier = Modifier) {
    CurvedTextSample()
}