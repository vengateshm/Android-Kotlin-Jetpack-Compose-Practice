package dev.vengateshm.compose_material3.message_bar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MessageBarState {
    var success by mutableStateOf<String?>(null)
        private set
    var error by mutableStateOf<Exception?>(null)
        private set
    internal var updated by mutableStateOf(false)
        private set

    fun addSuccess(message: String) {
        error = null
        success = message
        updated = !updated
    }

    fun addError(exception: Exception) {
        success = null
        error = exception
        updated = !updated
    }
}

@Composable
fun rememberMessageBarState(): MessageBarState {
    return remember { MessageBarState() }
}