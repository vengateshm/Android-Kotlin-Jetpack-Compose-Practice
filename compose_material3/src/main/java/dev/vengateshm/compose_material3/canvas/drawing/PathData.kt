package dev.vengateshm.compose_material3.canvas.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class PathData(
    val id: String,
    val color: Color,
    val path: List<Offset>,
)
