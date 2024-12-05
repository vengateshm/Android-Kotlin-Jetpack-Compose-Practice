package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import kotlinx.coroutines.flow.MutableSharedFlow

interface Navigator2 {
    val events: MutableSharedFlow<NavigationEvent>
    fun navigate(route: Any)
    fun popBackStack()
}

sealed class NavigationEvent {
    data object PopBackStack : NavigationEvent()
    data class Navigate(val route: Any) : NavigationEvent()
}