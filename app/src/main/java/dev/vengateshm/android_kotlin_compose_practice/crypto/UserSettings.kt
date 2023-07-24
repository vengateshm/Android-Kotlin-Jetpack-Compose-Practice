package dev.vengateshm.android_kotlin_compose_practice.crypto

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val username: String? = null,
    val password: String? = null,
)
