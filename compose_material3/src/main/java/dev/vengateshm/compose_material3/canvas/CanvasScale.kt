package dev.vengateshm.compose_material3.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CanvasScale(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        scale(scaleX = 10f, scaleY = 15f) {
            drawCircle(
                color = Color.Blue,
                radius = 20.dp.toPx()
            )
        }
    }
}

@Preview
@Composable
private fun CanvasScalePreview() {
    CanvasScale()
}