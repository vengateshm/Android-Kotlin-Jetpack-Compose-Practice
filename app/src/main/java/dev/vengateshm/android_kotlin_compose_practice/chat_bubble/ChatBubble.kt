package dev.vengateshm.android_kotlin_compose_practice.chat_bubble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ChatBubblePreview() {
    ChatBubble(message = "Happy New Year!")
}

@Composable
fun ChatBubble(message: String) {
    Column(modifier = Modifier.fillMaxWidth(0.7f)) {
        // Can be box or row
        Box(
            modifier =
                Modifier
                    .padding(start = 4.dp)
                    .size(8.dp)
                    // Can be background with shape or drawbehind
                    .drawBehind {
                        val path = Path()
                        val width = size.width
                        val height = size.height
                        path.apply {
                            moveTo(x = width / 2, y = 0f)
                            lineTo(x = width, y = height)
                            lineTo(x = 0f, y = height)
                        }
                        drawPath(path = path, color = Color.LightGray)
                    },
//                .background(
//                    color = Color.LightGray,
//                    shape = TriangleShape()
//                )
        ) {
        }
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(4.dp),
                    ),
        ) {
            Text(
                text = message,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
            )
        }
    }
}

class TriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path()
        val width = size.width
        val height = size.height
        path.apply {
            moveTo(x = width / 2, y = 0f)
            lineTo(x = width, y = height)
            lineTo(x = 0f, y = height)
        }
        return Outline.Generic(path)
    }
}
