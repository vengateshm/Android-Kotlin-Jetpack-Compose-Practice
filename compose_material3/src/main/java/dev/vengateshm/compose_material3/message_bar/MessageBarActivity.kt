package dev.vengateshm.compose_material3.message_bar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MessageBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
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
            }
        }
    }
}