package dev.vengateshm.kotlin_practice.testing.mockk

import java.util.UUID

data class User(val id: UUID, val email: String, val password: String)

class UserRepository {
    fun saveUser(email: String, password: String): UUID {
        return UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    }

    fun findUserByEmail(email: String): User? =
        User(
            id = UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"),
            email = "a@b.com",
            password = "password",
        )
}

class EmailService {
    fun sendEmail(to: String, subject: String, body: String) {
        println("Sending $body to $to with subject $subject")
    }
}

class UserService(
    private val userRepository: UserRepository,
    private val emailService: EmailService,
) {
    fun createUser(email: String, password: String): UUID {
        userRepository.findUserByEmail(email)
            ?.let { throw IllegalArgumentException("User with email $email already exists") }
        return userRepository.saveUser(email, password)
            .also { userId ->
                emailService.sendEmail(
                    to = email,
                    subject = "Welcome",
                    body = "Welcome to our platform $userId",
                )
            }
    }
}