package dev.vengateshm.compose_material3.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun TextWithGradientColor(modifier: Modifier = Modifier) {
    var size by remember {
        mutableStateOf(Size.Zero)
    }
    Text(
        modifier = Modifier
            .onSizeChanged {
                size = it.toSize()
            },
        text = "Good, Bye!",
        fontSize = 48.sp,
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.Red, Color.Blue, Color.Cyan, Color.Yellow
                ),
                start = Offset(0f, 0f),
                end = Offset(size.width * 0.5f, size.height)
            )
        )
    )
}