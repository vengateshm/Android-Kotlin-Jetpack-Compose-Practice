package dev.vengateshm.kotlin_practice.mockk

import dev.vengateshm.kotlin_practice.testing.mockk.EmailService
import dev.vengateshm.kotlin_practice.testing.mockk.User
import dev.vengateshm.kotlin_practice.testing.mockk.UserRepository
import dev.vengateshm.kotlin_practice.testing.mockk.UserService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verifyAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class UserServiceAnnotationsTest {
    @MockK
    lateinit var userRepository: UserRepository

    @MockK
    lateinit var emailService: EmailService

    @InjectMockKs
    lateinit var userService: UserService

    @Test
    fun `should throw exception when user already exists`() {
        val email = "a@b.com"
        val password = "password"

        val foundUser = User(UUID.randomUUID(), email, password)
        every {
            userRepository.findUserByEmail(
                match { it.contains("@") },
            )
        } returns foundUser

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
        } just runs

//        justRun {
//            emailService.sendEmail(
//                email,
//                "Welcome",
//                "Welcome to our platform $createdUUID",
//            )
//        }
//
//        every {
//            emailService.sendEmail(
//                email,
//                "Welcome",
//                "Welcome to our platform $createdUUID",
//            )
//        } answers { }

        val result = userService.createUser(email, password)
        assertEquals(createdUUID, result)

        verifyAll {
            userRepository.findUserByEmail(email)
            userRepository.saveUser(email, password)
        }
    }
}