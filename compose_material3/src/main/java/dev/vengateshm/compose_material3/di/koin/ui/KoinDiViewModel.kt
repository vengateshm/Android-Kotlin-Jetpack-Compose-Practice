package dev.vengateshm.compose_material3.di.koin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vengateshm.compose_material3.di.koin.domain.KoinDiUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class KoinDiViewModel(private val koinDiUseCase: KoinDiUseCase) : ViewModel() {
    private val _uiData = MutableStateFlow(KoinDiUiData())
    val uiData = _uiData.asStateFlow()

    init {
        viewModelScope.launch {
            _uiData.update { it.copy(isLoading = true) }
            koinDiUseCase.getData().let { data ->
                _uiData.update { it.copy(isLoading = false, data = data) }
            }
        }
    }
}