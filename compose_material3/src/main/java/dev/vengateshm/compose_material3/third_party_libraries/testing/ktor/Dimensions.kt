package dev.vengateshm.compose_material3.third_party_libraries.testing.ktor

import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
    val width: Double,
    val height: Double,
    val depth: Double,
)
