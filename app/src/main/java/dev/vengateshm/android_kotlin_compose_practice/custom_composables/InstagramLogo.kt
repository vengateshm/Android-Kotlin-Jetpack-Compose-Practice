package dev.vengateshm.android_kotlin_compose_practice.custom_composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InstagramLogo() {
    val colors = listOf(Color.Yellow, Color.Red, Color.Magenta)
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(15.dp)
    ) {
        drawRoundRect(
            brush = Brush.linearGradient(colors),
            cornerRadius = CornerRadius(40f),
            style = Stroke(width = 15f)
        )
        drawCircle(
            brush = Brush.linearGradient(colors),
            style = Stroke(width = 15f),
            radius = 45f
        )
        drawCircle(
            brush = Brush.linearGradient(colors),
            radius = 13f,
            center = Offset(
                x = size.width * 0.8f,
                y = size.height * 0.2f
            )
        )
        drawCircle(
            color = Color.White,
            radius = 4f,
            center = Offset(
                x = size.width * 0.8f,
                y = size.height * 0.2f
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InstagramLogoPreview() {
    InstagramLogo()
}