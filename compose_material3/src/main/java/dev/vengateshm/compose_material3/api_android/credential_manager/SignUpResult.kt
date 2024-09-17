package dev.vengateshm.compose_material3.api_android.credential_manager

sealed class SignUpResult {
    data class Success(val username: String) : SignUpResult()
    data object Cancelled : SignUpResult()
    data object Failure : SignUpResult()
}