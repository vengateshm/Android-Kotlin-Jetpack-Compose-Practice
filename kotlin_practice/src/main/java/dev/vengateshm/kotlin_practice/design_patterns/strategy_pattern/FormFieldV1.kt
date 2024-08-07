package dev.vengateshm.kotlin_practice.design_patterns.strategy_pattern

fun interface Validator {
    fun isValid(value: String): Boolean
}

class EmailValidator : Validator {
    override fun isValid(value: String) = value.contains("@") && value.contains(".")
}

class UsernameValidator : Validator {
    override fun isValid(value: String) = value.isNotBlank()
}

class PasswordValidator : Validator {
    override fun isValid(value: String) = value.length >= 8
}

val emailValidator = Validator { it.contains("@") && it.contains(".") }
val usernameValidator = Validator { it.isNotBlank() }
val passwordValidator = Validator { it.length >= 8 }

typealias Validator1 = (String) -> Boolean

val emailValidator1: Validator1 = { it.contains("@") && it.contains(".") }
val usernameValidator1: Validator1 = { it.isNotBlank() }
val passwordValidator1: Validator1 = { it.length >= 8 }

fun Validator1.optional(): Validator1 = { it.isEmpty() || this(it) }

class FormFieldV1(val name: String, val value: String, val validator: Validator1) {
    fun isValid() = validator.invoke(value)
}

fun main() {
    val emailField = FormFieldV1("email", "a@b.com", emailValidator1)
    val emailField1 = FormFieldV1("email", "", emailValidator1.optional())
    val usernameField = FormFieldV1("username", "vengateshm", usernameValidator1)
    val usernameField1 = FormFieldV1("username", "", usernameValidator1)
    val passwordField = FormFieldV1("password", "vengateshm123", passwordValidator1)
    println(emailField.isValid())
    println(emailField1.isValid())
    println(usernameField.isValid())
    println(usernameField1.isValid())
    println(passwordField.isValid())
}