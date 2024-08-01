package dev.vengateshm.compose_material3.api_compose.navigation.nested_typesafe

import kotlinx.serialization.Serializable

@Serializable
sealed class Dest {
    @Serializable
    data object Auth1 : Dest()

    @Serializable
    data object Auth2 : Dest()

    @Serializable
    data object Home1 : Dest()

    @Serializable
    data class Home2(val text: String) : Dest()
}
