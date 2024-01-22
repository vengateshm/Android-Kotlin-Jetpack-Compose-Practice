package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton

class PasswordValidator : FormFieldValidator {
    // private val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*!_.]).{8,}$".toRegex()

    override fun validate(text: String): FormFieldValidationResult {
        val errors = mutableListOf<ValidationError>()

        if (!text.matches("^.{8,}\$".toRegex())) {
            errors.add(ValidationError("Password must be at least 8 characters long."))
        }
        if (!text.contains("[A-Z]".toRegex())) {
            errors.add(ValidationError("Password must contain at least one uppercase letter."))
        }
        if (!text.contains("[a-z]".toRegex())) {
            errors.add(ValidationError("Password must contain at least one lowercase letter."))
        }
        if (!text.contains("[0-9]".toRegex())) {
            errors.add(ValidationError("Password must contain at least one number."))
        }
        if (!text.contains("[@#$%^&*!_.]".toRegex())) {
            errors.add(ValidationError("Password must contain at least one special character."))
        }

        return FormFieldValidationResult(errors.isNotEmpty(), errors)
    }
}
