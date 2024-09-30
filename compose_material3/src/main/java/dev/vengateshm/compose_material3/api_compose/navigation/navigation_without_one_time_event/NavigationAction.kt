package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.navigation.NavOptionsBuilder

sealed interface NavigationAction {
    data class NavigateTo(
        val dest1: Dest1,
        val navOptions: NavOptionsBuilder.() -> Unit = {},
    ) : NavigationAction

    data object NavigateUp : NavigationAction
}