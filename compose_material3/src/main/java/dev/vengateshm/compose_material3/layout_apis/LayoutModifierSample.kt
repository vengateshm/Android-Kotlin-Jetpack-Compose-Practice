package dev.vengateshm.compose_material3.layout_apis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp

@Composable
fun LayoutModifierSample(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(48.dp)
    ) {
        Element()
        Element(modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(
                constraints.copy(
                    maxWidth = constraints.maxWidth + 64.dp.roundToPx(),
                    minHeight = constraints.minHeight + 32.dp.roundToPx()
                )
            )
            layout(placeable.width, placeable.height) {
                placeable.place(0, 0)
            }
        })
        Element()
        Element()
    }
}