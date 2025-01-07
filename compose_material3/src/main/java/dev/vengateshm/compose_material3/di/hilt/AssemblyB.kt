package dev.vengateshm.compose_material3.di.hilt

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.vengateshm.compose_material3.ui_components.CenterAlignedContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Composable
fun AssemblyB(
    modifier: Modifier = Modifier,
    assemblyAViewModelFactory: AssemblyBViewModel.AssemblyBViewModelFactory,
) {
    val viewModel =
        remember { assemblyAViewModelFactory.create(Wheel(name = "Wheel assisted injection")) }
    val state by viewModel.state.collectAsStateWithLifecycle()

    CenterAlignedContent {
        Column {
            Text(text = state)
        }
    }
}

class AssemblyBViewModel @AssistedInject constructor(
    private val engine: Engine,
    @Assisted private val wheel: Wheel,
) : ViewModel() {

    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    init {
        _state.update {
            "Engine: ${engine.name} Wheel: ${wheel.name}"
        }
    }

    @AssistedFactory
    interface AssemblyBViewModelFactory {
        fun create(wheel: Wheel): AssemblyBViewModel
    }
}