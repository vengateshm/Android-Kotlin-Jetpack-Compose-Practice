package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import kotlinx.serialization.Serializable

sealed interface Dest1 {
    @Serializable
    data object HomeGraph : Dest1

    @Serializable
    data object AuthGraph : Dest1

    @Serializable
    data object Login : Dest1

    @Serializable
    data object Home : Dest1

    @Serializable
    data class Detail(val id: String) : Dest1
}