package dev.vengateshm.kotlin_practice.mockk

import dev.vengateshm.kotlin_practice.testing.mockk.EmailService
import dev.vengateshm.kotlin_practice.testing.mockk.User
import dev.vengateshm.kotlin_practice.testing.mockk.UserRepository
import dev.vengateshm.kotlin_practice.testing.mockk.UserService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID
import kotlin.test.assertEquals

class UserServiceTest {
    private val userRepository: UserRepository = mockk()
    private val emailService = mockk<EmailService>()

    private val userService = UserService(userRepository, emailService)

    @Test
    fun `should throw exception when user already exists`() {
        val email = "a@b.com"
        val password = "password"

        val foundUser = User(UUID.randomUUID(), email, password)
        every { userRepository.findUserByEmail(email) } returns foundUser

        assertThrows<IllegalArgumentException> {
            userService.createUser(email, password)
        }

        verifyAll { userRepository.findUserByEmail(email) }
    }

    @Test
    fun `should return UUID when user with given email successfully created`() {
        val email = "b@b.com"
        val password = "password1"
        val createdUUID = UUID.randomUUID()

        every { userRepository.findUserByEmail(email) } returns null
        every { userRepository.saveUser(email, password) } returns createdUUID
        every {
            emailService.sendEmail(
                email,
                "Welcome",
                "Welcome to our platform $createdUUID",
            )
        } returns Unit

        val result = userService.createUser(email, password)
        assertEquals(createdUUID, result)

        verifyAll {
            userRepository.findUserByEmail(email)
            userRepository.saveUser(email, password)
        }
    }
}