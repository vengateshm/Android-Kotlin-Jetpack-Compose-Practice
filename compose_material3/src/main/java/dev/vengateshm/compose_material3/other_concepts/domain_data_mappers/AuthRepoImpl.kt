package dev.vengateshm.compose_material3.other_concepts.domain_data_mappers

class AuthRepoImpl(private val mapper: Mapper<SignIn, SignInDto>) : AuthRepo {
    override fun signIn(signIn: SignIn): Result<String> {
        return try {
            val signInDto = signIn.toSignInDto()
            if (signInDto.email.contains("@email.com"))
                Result.success("Success")
            else
                throw Exception("Error")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun signInMapper(signIn: SignIn): Result<String> {
        return try {
            val signInDto = mapper.map(signIn)
            if (signInDto.email.contains("@email.com"))
                Result.success("Success")
            else
                Result.failure(Exception("Error"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}