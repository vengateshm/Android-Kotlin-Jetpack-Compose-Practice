package dev.vengateshm.compose_material3

import dev.vengateshm.compose_material3.other_concepts.domain_data_mappers.AuthRepoImpl
import dev.vengateshm.compose_material3.other_concepts.domain_data_mappers.SignIn
import dev.vengateshm.compose_material3.other_concepts.domain_data_mappers.SignInDto
import dev.vengateshm.compose_material3.other_concepts.domain_data_mappers.SignInToSignInDtoMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

class DomainDataMapperTest {
    @Test
    fun `without mapper`() {
        val mapper = mockk<SignInToSignInDtoMapper>()
        val authRepoImpl = AuthRepoImpl(mapper)
        val signIn = SignIn(
            firstName = "firstName",
            lastName = "lastName",
            age = 10,
            password = "password",
        )
        val result = authRepoImpl.signIn(signIn)
        assertEquals(
            "error",
            result.exceptionOrNull()?.message,
        ) // cannot test error logic as extensions cannot be mocked
    }

    @Test
    fun `with mapper wrong email`() {
        val mapper = mockk<SignInToSignInDtoMapper>()
        val authRepoImpl = AuthRepoImpl(mapper)
        val signIn = SignIn(
            firstName = "firstName",
            lastName = "lastName",
            age = 10,
            password = "password",
        )
        every { mapper.map(signIn) } returns SignInDto(email = "", password = "password")
        val result = authRepoImpl.signInMapper(signIn)
        assertEquals(
            "Error",
            result.exceptionOrNull()?.message,
        )
    }

    @Test
    fun `with mapper correct email`() {
        val mapper = mockk<SignInToSignInDtoMapper>()
        val authRepoImpl = AuthRepoImpl(mapper)
        val signIn = SignIn(
            firstName = "firstName",
            lastName = "lastName",
            age = 10,
            password = "password",
        )
        every { mapper.map(signIn) } returns SignInDto(
            email = "firstNamelastName10@email.com",
            password = "password",
        )
        val result = authRepoImpl.signInMapper(signIn)
        assertEquals(
            null,
            result.exceptionOrNull()?.message,
        )
    }
}