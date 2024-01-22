package dev.vengateshm.android_kotlin_compose_practice.layout_change_keyboard_open

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val GC1 = Color(0XFF0D4C92)
val GC2 = Color(0XFF59C1BD)

@Composable
fun GradientBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier =
            modifier
                .background(
                    brush = Brush.linearGradient(listOf(GC1, GC2)),
                ),
    ) {
        content()
    }
}
