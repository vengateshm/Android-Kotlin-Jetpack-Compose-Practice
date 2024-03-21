package dev.vengateshm.compose_material3.clean_error_handling

interface AuthRepository {
    suspend fun register(password: String): Result<AuthUser, DataError.Network>
}

data class AuthUser(
    val fullName: String,
    val token: String,
    val email: String
)