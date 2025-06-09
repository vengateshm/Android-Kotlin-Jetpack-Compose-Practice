package dev.vengateshm.compose_material3.ui_concepts.bottom_navigation_with_nav_drawer

import kotlinx.serialization.Serializable

@Serializable
sealed class AppDestination {
    @Serializable
    data object AuthDestination : AppDestination()

    @Serializable
    data object MainDestination : AppDestination()

    @Serializable
    data object SettingsDestination : AppDestination()

    @Serializable
    data class HomeDestination(val id: String) : AppDestination()

    @Serializable
    data object AccountDestination : AppDestination()
}