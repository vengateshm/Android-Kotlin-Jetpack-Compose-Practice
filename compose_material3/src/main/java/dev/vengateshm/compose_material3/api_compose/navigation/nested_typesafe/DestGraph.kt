package dev.vengateshm.compose_material3.api_compose.navigation.nested_typesafe

import kotlinx.serialization.Serializable

@Serializable
sealed class DestGraph {
    @Serializable
    data object AuthGraph : DestGraph()

    @Serializable
    data object HomeGraph : DestGraph()
}