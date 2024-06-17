package dev.vengateshm.kotlin_practice.clean_code_tips

const val MIN_PASSWORD = 6

// Here the minimum password length may vary
// and name doesn't convey exact functionality
fun checkPasswordLength(password: String): Boolean {
    return password.length >= MIN_PASSWORD
}

// Better one
const val MIN_PASSWORD_LENGTH = 6

fun isPasswordLongEnough(password: String): Boolean {
    return password.length >= MIN_PASSWORD_LENGTH
}

fun main() {
    checkPasswordLength("")
    isPasswordLongEnough("")
}