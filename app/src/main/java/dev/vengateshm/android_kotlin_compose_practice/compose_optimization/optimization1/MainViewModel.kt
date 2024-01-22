package dev.vengateshm.android_kotlin_compose_practice.compose_optimization.optimization1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var color by mutableStateOf(Color.Red)
        private set

    fun changeColor(color: Color) {
        this.color = color
    }
}
