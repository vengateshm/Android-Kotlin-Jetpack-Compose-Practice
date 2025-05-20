package dev.vengateshm.compose_material3.third_party_libraries.testing.ktor

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String,
)
