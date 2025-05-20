package dev.vengateshm.compose_material3.third_party_libraries.testing.ktor

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int,
)