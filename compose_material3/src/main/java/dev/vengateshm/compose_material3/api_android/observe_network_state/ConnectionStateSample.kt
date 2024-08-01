package dev.vengateshm.compose_material3.api_android.observe_network_state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun ConnectionStateSample(modifier: Modifier = Modifier) {
    val connectionState by rememberConnectionState()

    val isConnected by remember(connectionState) {
        derivedStateOf {
            connectionState === ConnectionState.Available
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = if (isConnected) "Connected" else "UnAvailable")
    }
}