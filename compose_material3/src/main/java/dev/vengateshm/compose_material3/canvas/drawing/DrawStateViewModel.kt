package dev.vengateshm.compose_material3.canvas.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DrawStateViewModel : ViewModel() {
    private val _state = MutableStateFlow(DrawState())
    val state = _state.asStateFlow()

    fun onDrawAction(drawAction: DrawAction) {
        when (drawAction) {
            DrawAction.OnClear -> clearCanvas()
            DrawAction.OnPathEnd -> onPathEnd()
            is DrawAction.OnPathMove -> onPathMove(drawAction.offset)
            DrawAction.OnPathStart -> onPathStart()
            is DrawAction.OnSelectColor -> onSelectColor(drawAction.color)
        }
    }

    private fun onSelectColor(color: Color) {
        _state.update {
            it.copy(selectedColor = color)
        }
    }

    private fun onPathStart() {
        _state.update {
            it.copy(
                currentPath = PathData(
                    id = System.currentTimeMillis().toString(),
                    color = it.selectedColor,
                    path = emptyList(),
                ),
            )
        }
    }

    private fun onPathMove(offset: Offset) {
        val currentPath = _state.value.currentPath ?: return
        _state.update {
            it.copy(
                currentPath = currentPath.copy(
                    path = currentPath.path + offset,
                ),
            )
        }
    }

    private fun onPathEnd() {
        val currentPath = _state.value.currentPath ?: return
        _state.update {
            it.copy(
                currentPath = null,
                paths = it.paths + currentPath,
            )
        }
    }

    private fun clearCanvas() {
        _state.update {
            it.copy(
                currentPath = null,
                paths = emptyList(),
            )
        }
    }
}