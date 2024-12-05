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
fun EditScreen(viewModel: EditScreenViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Edit")
        Button(onClick = { viewModel.goBack() }) {
            Text(text = "Go back")
        }
    }
}

class EditScreenViewModel(
    private val navigator2: Navigator2,
) : ViewModel() {
    fun goBack() {
        navigator2.popBackStack()
    }
}