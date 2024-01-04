package dev.vengateshm.kotlin_practice

import dev.vengateshm.kotlin_practice.junit5.User
import dev.vengateshm.kotlin_practice.junit5.UserAlreadyExistsException
import dev.vengateshm.kotlin_practice.junit5.UserService
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.function.ThrowingRunnable
import org.junit.jupiter.api.BeforeEach

class UserServiceTest {
    val userService = UserService()

    @BeforeEach
    fun beforeEach() {
        userService.clearAllUsers()
    }

    @Test
    fun testUserRegistration() {
        userService.registerUser(User("John Doe"))
        assertTrue(userService.isRegistered("John Doe"))
    }

    @Test
    fun testDuplicateUserRegistration() {
        userService.registerUser(User("John Doe"))
        assertThrows(
            UserAlreadyExistsException::class.java
        ) { userService.registerUser(User("John Doe")) }
    }
}