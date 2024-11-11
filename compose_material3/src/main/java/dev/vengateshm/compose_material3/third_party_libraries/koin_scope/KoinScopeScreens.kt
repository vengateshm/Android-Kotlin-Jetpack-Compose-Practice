package dev.vengateshm.compose_material3.third_party_libraries.koin_scope

import kotlinx.serialization.Serializable

@Serializable
sealed interface KoinScopeScreens {
    @Serializable
    data object Entry : KoinScopeScreens

    @Serializable
    data object Auth : KoinScopeScreens

    @Serializable
    data object Landing : KoinScopeScreens
}