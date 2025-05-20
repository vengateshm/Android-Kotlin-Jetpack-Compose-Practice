package dev.vengateshm.compose_material3.third_party_libraries.testing.ktor

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val rating: Int,
    val comment: String,
    val date: String, // You might want to parse this to a Date/LocalDateTime object later
    val reviewerName: String,
    val reviewerEmail: String,
)
