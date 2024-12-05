package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = {
                viewModel.navigateToSettings()
            },
        ) {
            Text(text = "Go to Settings")
        }
    }
}

class ProfileViewModel(
    private val navigator1: Navigator1,
) : ViewModel() {
    fun navigateToSettings() {
        navigator1.navigate(Dest1.Settings)
    }
}