package dev.vengateshm.compose_material3.utils

val AT_LEAST_ONE_SMALL_LETTER = "(.*[a-z])".toRegex()
val AT_LEAST_ONE_CAPITAL_LETTER = "(.*[A-Z])".toRegex()
val AT_LEAST_ONE_NUMBER = "(.*[0-9])".toRegex()
val AT_LEAST_ONE_SYMBOL = "(.*[@!#$%^&+=?])".toRegex()
val HAS_MINIMUM_CHARACTERS = "(.{8,})".toRegex()
val NO_SPACE = " ".toRegex()

fun String.hasAtLeastOneSmallLetter() = this.contains(AT_LEAST_ONE_SMALL_LETTER)
fun String.hasAtLeastOneCapitalLetter() = this.contains(AT_LEAST_ONE_CAPITAL_LETTER)
fun String.hasAtLeastOneNumber() = this.contains(AT_LEAST_ONE_NUMBER)
fun String.hasAtLeastOneSpecialCharacter() = this.contains(AT_LEAST_ONE_SYMBOL)
fun String.hasMinimumLength() = this.contains(HAS_MINIMUM_CHARACTERS)
fun String.hasNoSpace() = this.contains(NO_SPACE).not()

val PASSWORD_STRENGTH_REGEX =
    "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*+=])(?!.*\\s).{8,}$".toRegex()

fun String.isStrongPassword() = this.contains(PASSWORD_STRENGTH_REGEX)

data class PasswordValidationResult(val isValid: Boolean, val msg: String)

fun String.passwordValidationResult(): List<PasswordValidationResult> {
    return listOf(
        PasswordValidationResult(
            this.hasAtLeastOneSmallLetter(),
            "Should contain one small letter"
        ),
        PasswordValidationResult(
            this.hasAtLeastOneCapitalLetter(),
            "Should contain one capital letter"
        ),
        PasswordValidationResult(this.hasAtLeastOneNumber(), "Should contain one number"),
        PasswordValidationResult(
            this.hasAtLeastOneSpecialCharacter(),
            "Should contain special character !@#$%^&*+="
        ),
        PasswordValidationResult(this.hasMinimumLength(), "Should be atleast 8 characters long"),
        PasswordValidationResult(this.hasNoSpace(), "Should not contain space"),
    )
}