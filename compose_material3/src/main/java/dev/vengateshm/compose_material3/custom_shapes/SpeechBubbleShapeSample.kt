package dev.vengateshm.compose_material3.custom_shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SpeechBubbleShapeSample() {
    val tipSize = with(LocalDensity.current) { 15.dp.toPx() }
    val cornerRadius = with(LocalDensity.current) { 15.dp.toPx() }

    Box(
        modifier = Modifier
            .size(200.dp)
            /*.drawBehind {
                val path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            left = tipSize,
                            top = 0f,
                            right = size.width,
                            bottom = size.height - tipSize,
                            radiusX = cornerRadius,
                            radiusY = cornerRadius
                        )
                    )
                    moveTo(
                        x = tipSize,
                        y = size.height - tipSize - cornerRadius
                    )
                    lineTo(
                        x = 0f,
                        y = size.height
                    )
                    lineTo(
                        x = tipSize + cornerRadius,
                        y = size.height - tipSize
                    )
                    close()
                }
                drawPath(
                    path = path,
                    color = Color.Red
                )
            }*/
            .clip(SpeechBubbleShape())
            .background(color = Color.Red)
    ) {
        Text(
            modifier = Modifier.offset(x = 15.dp, y = 15.dp),
            text = "Hello World!"
        )
    }
}

@Preview
@Composable
private fun SpeechBubbleShapeSamplePreview() {
    SpeechBubbleShapeSample()
}