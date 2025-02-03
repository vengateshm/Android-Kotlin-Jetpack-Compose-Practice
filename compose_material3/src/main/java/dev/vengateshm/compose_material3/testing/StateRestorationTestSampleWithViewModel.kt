package dev.vengateshm.compose_material3.testing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StateRestorationTestSampleWithViewModel(modifier: Modifier = Modifier) {
    val viewModel = viewModel<StateRestorationTestSampleViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(modifier = Modifier.testTag("TT"), text = viewModel.text)
        Button(
            modifier = Modifier.testTag("BB"),
            onClick = {
                viewModel.updateText("Loaded")
            },
        ) {
            Text("Click me!")
        }
    }
}

class StateRestorationTestSampleViewModel : ViewModel() {
    fun updateText(string: String) {
        text = string
    }

    var text by mutableStateOf("Loading...")
}