package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Settings")
        Button(onClick = { viewModel.goBack() }) {
            Text(text = "Go back")
        }
    }
}

class SettingsViewModel(
    private val navigator1: Navigator1,
) : ViewModel() {
    fun goBack() {
        navigator1.popBackStack()
    }
}