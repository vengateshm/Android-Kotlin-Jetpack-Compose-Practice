package dev.vengateshm.compose_material3.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CanvasInset(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val size = size / 3f
        inset(horizontal = 100f, vertical = 200f) {
            drawRect(
                color = Color.Yellow,
                size = size
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CanvasInsetPreview() {
    CanvasInset()
}