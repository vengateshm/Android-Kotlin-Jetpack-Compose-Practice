package dev.vengateshm.kotlin_practice.version_changes

// Name-based destructuring

data class User(val firstName:String, val dateOfBirth:String)

fun main() {
    val (name, dob) = User("Alice", "1970/01/01")
}