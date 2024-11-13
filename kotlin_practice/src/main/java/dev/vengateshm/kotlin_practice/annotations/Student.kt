package dev.vengateshm.kotlin_practice.annotations

import dev.vengateshm.kotlin_practice.annotations.Constants.VALID_EMAIL_REGEX_STRING
import dev.vengateshm.kotlin_practice.annotations.Constants.VALID_STRING_REGEX_STRING

object Constants {
    const val VALID_EMAIL_REGEX_STRING = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"
    const val VALID_STRING_REGEX_STRING = "^\\w{0,10}\$"
}

@Target(AnnotationTarget.FIELD)
annotation class ValidEmail(val regex: String = VALID_EMAIL_REGEX_STRING)

@Target(AnnotationTarget.FIELD)
annotation class ValidString(val regex: String = VALID_STRING_REGEX_STRING)

@Target(AnnotationTarget.FIELD)
annotation class VIPFields

@Target(AnnotationTarget.FIELD)
annotation class Summable

data class Student(
    @ValidString val username: String,
    @ValidEmail val email: String,
    @VIPFields val age: Int,
    @VIPFields val country: String,
    @Summable val english: Int,
    @Summable val maths: Int,
    @Summable val science: Int,
) {
    init {
        this::class.java.declaredFields.forEach { field ->
            if (field.isAnnotationPresent(ValidEmail::class.java)) {
                val regex = field.getAnnotation(ValidEmail::class.java).regex
                if (!regex.toRegex().matches(email)) {
                    throw IllegalArgumentException("Invalid email")
                }
            }
            if (field.isAnnotationPresent(ValidString::class.java)) {
                val regex = field.getAnnotation(ValidString::class.java).regex
                if (!regex.toRegex().matches(username)) {
                    throw IllegalArgumentException("Invalid username")
                }
            }
        }
    }

    fun printVIPFields() {
        this::class.java.declaredFields.forEach { field ->
            if (field.isAnnotationPresent(VIPFields::class.java)) {
                println("${field.name} ${field.get(this)}")
            }
        }
    }

    fun printTotalMarks() {
        var totalMarks = 0
        this::class.java.declaredFields.forEach { field ->
            if (field.isAnnotationPresent(Summable::class.java)) {
                totalMarks += field.getInt(this)
            }
        }
        println(totalMarks)
    }
}

fun main() {
    val student = Student(
        username = "john_doe",
        email = "john.doe@example.com",
        age = 21,
        country = "USA",
        english = 85,
        maths = 90,
        science = 88,
    )
    student.printVIPFields()
    student.printTotalMarks()
}