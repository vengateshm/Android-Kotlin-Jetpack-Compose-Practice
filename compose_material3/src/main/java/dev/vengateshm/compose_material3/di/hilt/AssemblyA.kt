package dev.vengateshm.compose_material3.di.hilt

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vengateshm.compose_material3.ui_components.CenterAlignedContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun AssemblyA(
    modifier: Modifier = Modifier,
    goToNext: () -> Unit,
) {
    val viewmodel = hiltViewModel<AssemblyAViewModel>()
    val state by viewmodel.state.collectAsStateWithLifecycle()

    CenterAlignedContent {
        Column {
            Text(text = state.name)
            Button(onClick = goToNext) {
                Text(text = "Go to next")
            }
        }
    }
}

@HiltViewModel
class AssemblyAViewModel @Inject constructor(
    private val transmission: Transmission,
) : ViewModel() {
    private val _state = MutableStateFlow(Transmission(name = ""))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(name = transmission.name) }
        }
    }
}