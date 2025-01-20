package dev.vengateshm.compose_material3.api_kotlin.coroutines

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Composable
fun FlowCombineIssueSample(modifier: Modifier = Modifier) {
    val viewModel = viewModel<FlowCombineIssueViewModel>()

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (state.value.isLoading) {
            Column {
                Text(text = "Loading...")
                CircularProgressIndicator()
            }
        } else {
            Column {
                Text(text = state.value.first)
            }
        }
    }
}

data class FlowCombineUiState(
    val first: String = "",
    val isLoading: Boolean = true,
)

class FlowCombineIssueViewModel : ViewModel() {
    private val firstFlow: Flow<String> = getFirstFlow().onStart { emit("") }
    private val secondFlow: Flow<String> = emptyFlow<String>().onStart { emit("") }

    private val _uiState = MutableStateFlow(FlowCombineUiState())

    // At least one emission required from all the flows when using combine operator
    val uiState: StateFlow<FlowCombineUiState> =
        combine(
            _uiState,
            firstFlow,
            secondFlow,
        ) { state, first, second ->
            state.copy(first = first)
        }.onStart {
            refreshState()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    private fun refreshState() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(2000L)
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}

fun getFirstFlow() = flow<String> {
    emit("First")
}