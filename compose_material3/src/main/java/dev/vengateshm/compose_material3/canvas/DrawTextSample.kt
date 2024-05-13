package dev.vengateshm.compose_material3.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.theme.Material3AppTheme

@Composable
fun DrawTextSample(modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()
    val text = "Coding in Kotlin"
    val textStyle = TextStyle(
        color = Color.DarkGray,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(text),
        style = textStyle
    )
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        val width = size.width
        val height = size.height

        drawLine(
            color = Color.Black,
            start = Offset(x = 0f, y = height / 2),
            end = Offset(x = width, y = height / 2)
        )
        drawLine(
            color = Color.Black,
            start = Offset(x = width / 2, y = 0f),
            end = Offset(x = width / 2, y = height)
        )

        rotate(45f) {
            drawText(
                textMeasurer = textMeasurer,
                text = text,
                style = textStyle,
                topLeft = Offset(
                    x = center.x - textLayoutResult.size.width / 2,
                    y = center.y - textLayoutResult.size.height / 2
                )
            )
        }
    }
}

@Preview
@Composable
private fun DrawTextSamplePreview() {
    Material3AppTheme {
        DrawTextSample()
    }
}