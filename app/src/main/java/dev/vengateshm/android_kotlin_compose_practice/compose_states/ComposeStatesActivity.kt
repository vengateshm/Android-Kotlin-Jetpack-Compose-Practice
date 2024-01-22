package dev.vengateshm.android_kotlin_compose_practice.compose_states

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.AndroidKotlinComposePracticeTheme

class ComposeStatesActivity : ComponentActivity() {
    private val viewModel: ComposeStatesViewModel by viewModels()
    private var state by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidKotlinComposePracticeTheme {
                // ComposeStatesScreen(viewModel)
                LaunchedEffectTest()
            }
        }
    }

    @Composable
    fun ComposeStatesScreen(viewModel: ComposeStatesViewModel) {
        var rememberState by remember { mutableStateOf(0) }
        var rememberSaveableState by rememberSaveable { mutableStateOf(0) }

        Column(modifier = Modifier.fillMaxWidth()) {
            // Does not retains on rotation but on recomposition
            Button(onClick = {
                state++
            }) {
                Text(text = "state $state")
            }
            // Does not retains on rotation but on recomposition
            Button(onClick = {
                rememberState++
            }) {
                Text(text = "rememberState $rememberState")
            }
            // Retains on rotation
            Button(onClick = {
                rememberSaveableState++
            }) {
                Text(text = "rememberSaveableState $rememberSaveableState")
            }
            // Retains on rotation
            Button(onClick = {
                viewModel.setViewModelState()
            }) {
                Text(text = "stateViewModel ${viewModel.stateViewModel}")
            }
            // Retains on rotation and process death
            Button(onClick = {
                viewModel.setSavedStateHandleState()
            }) {
                Text(text = "savedStateHandleState ${viewModel.savedStateHandleState}")
            }
        }
    }
}
