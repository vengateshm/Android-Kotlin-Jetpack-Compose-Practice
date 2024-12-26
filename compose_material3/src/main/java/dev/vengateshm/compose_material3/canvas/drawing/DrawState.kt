package dev.vengateshm.compose_material3.canvas.drawing

import androidx.compose.ui.graphics.Color

data class DrawState(
    val selectedColor: Color = Color.Black,
    val currentPath: PathData? = null,
    val paths: List<PathData> = emptyList(),
)
