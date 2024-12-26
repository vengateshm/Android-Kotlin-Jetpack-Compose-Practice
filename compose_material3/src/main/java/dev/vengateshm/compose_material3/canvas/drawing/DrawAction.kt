package dev.vengateshm.compose_material3.canvas.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

sealed interface DrawAction {
    data object OnPathStart : DrawAction
    data object OnPathEnd : DrawAction
    data class OnPathMove(val offset: Offset) : DrawAction
    data object OnClear : DrawAction
    data class OnSelectColor(val color: Color) : DrawAction
}