package dev.vengateshm.compose_material3.apps.color_picker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ColorPickerViewModel : ViewModel() {
    var selectedColor by mutableStateOf(Color.Red)
        private set

    fun updateColor(newColor: Color) {
        selectedColor = newColor
    }
}