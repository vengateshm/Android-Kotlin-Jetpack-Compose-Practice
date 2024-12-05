package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class Navigator2Impl : Navigator2 {
    override val events = MutableSharedFlow<NavigationEvent>()
    private val scope = CoroutineScope(Dispatchers.IO)
    override fun navigate(route: Any) {
        scope.launch {
            events.emit(NavigationEvent.Navigate(route))
        }
    }

    override fun popBackStack() {
        scope.launch {
            events.emit(NavigationEvent.PopBackStack)
        }
    }
}