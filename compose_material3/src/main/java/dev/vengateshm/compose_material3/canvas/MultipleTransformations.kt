package dev.vengateshm.compose_material3.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MultipleTransformations(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        withTransform(
            transformBlock = {
                translate(left = size.width / 5f)
                rotate(degrees = 20f)
            },
            drawBlock = {
                drawRect(
                    color = Color.Gray,
                    topLeft = Offset(x = 100f, y = 100f),
                    size = Size(200.dp.toPx(), 100.dp.toPx())
                )
            })
    }
}

@Preview(showBackground = true)
@Composable
private fun MultipleTransformationsPreview() {
    MultipleTransformations()
}