package dev.vengateshm.compose_material3.other_concepts.initial_ui_loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InitialUiLoadingViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            // Cache values until 5 seconds, config changes wont last for more than 5 seconds
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
            false,
        )

    fun loadData() {
        println("Loading data...")
        viewModelScope.launch {
            _isLoading.value = true
            delay(3000L)
            _isLoading.value = false
        }
    }
}