package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based

enum class ErrorMessage(val msg: String) {
    PasswordMinimumLength("Please enter password of at least 8 characters long"),
    PasswordMaximumLength("Please enter of at most 15 characters long"),
    PasswordOneUpperCase("Password should contain at least one upper case letter"),
    PasswordOneLowerCase("Password should contain at least one lower case letter"),
    PasswordOneDigit("Password should contain at least one number"),
    InvalidEmail("Please enter a valid email address"),
    InvalidZipCode("Please enter a valid ZIP code"),
}