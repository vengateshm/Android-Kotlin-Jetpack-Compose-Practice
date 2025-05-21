package dev.vengateshm.compose_material3.recompositions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProgressViewModel : ViewModel() {

    private val _progress = MutableStateFlow(0.0f)
    val progress: StateFlow<Float> = _progress

    fun startProgress() {
        viewModelScope.launch {
            // Reset progress if it's already at 1.0f or allow re-trigger
            if (_progress.value >= 1.0f) {
                _progress.value = 0.0f
            }

            while (_progress.value < 1.0f) {
                delay(10) // 10 ms delay
                // Ensure we don't exceed 1.0f due to floating point inaccuracies
                val currentVal = _progress.value + 0.1f
                _progress.value = if (currentVal > 1.0f) 1.0f else currentVal.toFloat()
            }
        }
    }
}