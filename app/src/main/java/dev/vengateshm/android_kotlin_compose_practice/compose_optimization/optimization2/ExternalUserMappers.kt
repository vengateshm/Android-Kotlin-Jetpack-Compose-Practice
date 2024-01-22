package dev.vengateshm.android_kotlin_compose_practice.compose_optimization.optimization2

import dev.vengateshm.common_lib.ExternalUser

data class User(
    val id: String,
    val email: String,
    val username: String,
)

fun ExternalUser.toUser(): User {
    return User(
        id = id,
        email = email,
        username = username,
    )
}

fun User.toExternalUser(): ExternalUser {
    return ExternalUser(
        id = id,
        email = email,
        username = username,
    )
}
