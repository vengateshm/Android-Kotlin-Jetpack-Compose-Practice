package dev.vengateshm.compose_material3.other_concepts.domain_data_mappers

class SignInToSignInDtoMapper : Mapper<SignIn, SignInDto> {
    override fun map(from: SignIn): SignInDto {
        return SignInDto(
            email = from.firstName.lowercase().plus(from.lastName.lowercase()).plus(from.age)
                .plus("@email.com"),
            password = from.password,
        )
    }
}