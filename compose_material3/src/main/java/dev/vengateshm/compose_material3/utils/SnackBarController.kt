package dev.vengateshm.compose_material3.utils

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

data class SnackBarEvent(val message: String, val action: SnackBarAction? = null)
data class SnackBarAction(val name: String, val action: suspend () -> Unit)

object SnackBarController {
    private val _event = Channel<SnackBarEvent>()
    val events = _event.receiveAsFlow()

    suspend fun sendEvent(event: SnackBarEvent) {
        _event.send(event)
    }
}