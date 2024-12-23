package dev.vengateshm.compose_material3.other_concepts.domain_data_mappers

interface AuthRepo {
    fun signIn(signIn: SignIn): Result<String>
    fun signInMapper(signIn: SignIn): Result<String>
}