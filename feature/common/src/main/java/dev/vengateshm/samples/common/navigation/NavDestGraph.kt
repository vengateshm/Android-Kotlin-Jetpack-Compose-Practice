package dev.vengateshm.samples.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestGraph {
    @Serializable
    data object AuthGraph : NavDestGraph()

    @Serializable
    data object HomeGraph : NavDestGraph()
}
