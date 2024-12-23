package dev.vengateshm.compose_material3.other_concepts.domain_data_mappers

data class SignIn(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val password: String,
)

fun SignIn.toSignInDto(): SignInDto {
    return SignInDto(
        email = firstName.lowercase().plus(lastName.lowercase()).plus(age).plus("@email.com"),
        password = password,
    )
}