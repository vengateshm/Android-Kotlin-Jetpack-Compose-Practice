package dev.vengateshm.kotlin_practice.std_library_samples

@JvmInline
value class Email(val email: String) {
    init {
        if (email.contains("@").not()) {
            throw IllegalArgumentException("Invalid email")
        }
    }
}

fun main() {
    val email = Email("vengateshm@")
    val email2 = Email("vengateshm")
}