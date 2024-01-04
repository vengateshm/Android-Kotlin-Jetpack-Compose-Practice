package dev.vengateshm.kotlin_practice.junit5

data class User(val name: String)

class UserAlreadyExistsException : RuntimeException()

class UserService {
    var users = mutableListOf<User>()

    fun registerUser(user: User) {
        if (users.contains(user)) {
            throw UserAlreadyExistsException()
        } else {
            users.add(user)
        }
    }

    fun isRegistered(name: String): Boolean = users.find { it.name == name } != null

    fun clearAllUsers() {
        users.clear()
    }
}