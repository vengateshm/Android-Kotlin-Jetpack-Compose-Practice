package dev.vengateshm.compose_material3.api_android.credential_manager

sealed class SignInResult {
    data class Success(val username: String) : SignInResult()
    data object Cancelled : SignInResult()
    data object Failure : SignInResult()
    data object NoCredentials : SignInResult()
}