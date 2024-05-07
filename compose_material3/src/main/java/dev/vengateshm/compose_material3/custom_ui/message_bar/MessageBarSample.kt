package dev.vengateshm.compose_material3.custom_ui.message_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MessageBarSample(modifier: Modifier = Modifier) {
    val messageBarState = rememberMessageBarState()
    ContentWithMessageBar(
        modifier = Modifier.fillMaxSize(),
        messageBarState = messageBarState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    messageBarState.addSuccess("Bookmarked Successfully!")
                }) {
                Text(text = "Success")
            }
            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    messageBarState.addError(Exception("Internet Unavailable"))
                }) {
                Text(text = "Error")
            }
        }
    }
}