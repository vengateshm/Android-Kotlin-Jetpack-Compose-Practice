package dev.vengateshm.compose_material3.other_concepts.lru_cache

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val status: String
)