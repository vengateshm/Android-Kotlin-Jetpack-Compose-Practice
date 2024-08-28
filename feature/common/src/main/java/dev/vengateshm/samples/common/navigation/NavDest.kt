package dev.vengateshm.samples.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavDest {
    @Serializable
    data object Auth : NavDest()

    @Serializable
    data object Home : NavDest()
}
