package dev.vengateshm.compose_material3.api_compose.custom_modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.floor

class DottedBackgroundModifier(
    private val dotRadius: Float = 8f,
    private val color: Color = Color.LightGray
) : DrawModifier {
    override fun ContentDrawScope.draw() {
        drawBackground()
        drawContent()
    }

    private fun ContentDrawScope.drawBackground() {
        val diameter = dotRadius * 2
        val rows = floor(size.height / diameter.coerceAtMost(size.height)).toInt()
        val cols = floor(size.width / diameter.coerceAtMost(size.width)).toInt()
        for (rowIndex in 0 until rows) {
            for (columnIndex in 0 until cols) {
                drawCircle(
                    color = color,
                    radius = dotRadius,
                    center = Offset(
                        x = columnIndex * diameter + dotRadius,
                        y = rowIndex * diameter + dotRadius
                    )
                )
            }
        }
    }
}

fun Modifier.dottedBackground(dotRadius: Float, color: Color) =
    this.then(DottedBackgroundModifier(dotRadius = dotRadius, color = color))

@Composable
fun DottedBackgroundModifierSample() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .background(color = Color.Black)
                .dottedBackground(dotRadius = 8f, color = Color.LightGray),
            onClick = { }) {
            Text(text = "I'm a Button")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun DottedBackgroundModifierSamplePreview() {
    DottedBackgroundModifierSample()
}