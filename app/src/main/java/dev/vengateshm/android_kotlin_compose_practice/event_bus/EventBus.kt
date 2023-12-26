package dev.vengateshm.android_kotlin_compose_practice.event_bus

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

sealed class AppEvent {
    data class MessageEvent(val message: String) : AppEvent()
}

object EventBus {
    private val _event = MutableSharedFlow<AppEvent>()
    private val event: SharedFlow<AppEvent> = _event

    suspend fun sendEvent(event: AppEvent) {
        _event.emit(event)
    }

    fun observeEvent() = event
}