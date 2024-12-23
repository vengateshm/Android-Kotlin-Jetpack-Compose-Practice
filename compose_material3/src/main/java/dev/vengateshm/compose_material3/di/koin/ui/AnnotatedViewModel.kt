package dev.vengateshm.compose_material3.di.koin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vengateshm.compose_material3.di.koin.Human
import dev.vengateshm.compose_material3.di.koin.TestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class AnnotatedViewModel(
    private val testUseCase: TestUseCase,
    @InjectedParam private val human: Human,
) : ViewModel() {
    private val _uiData = MutableStateFlow(KoinDiUiData())
    val uiData = _uiData.asStateFlow()

    init {
        viewModelScope.launch {
            _uiData.update { it.copy(isLoading = true) }
            testUseCase.getData().let { data ->
                _uiData.update { it.copy(isLoading = false, data = data.plus(human.name)) }
            }
        }
    }
}