package dev.vengateshm.compose_material3.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CanvasTranslate(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        translate(left = 100f, top = 20f) {
            drawCircle(
                color = Color.Blue,
                radius = 200.dp.toPx()
            )
        }
    }
}

@Preview
@Composable
private fun CanvasTranslatePreview() {
    CanvasTranslate()
}