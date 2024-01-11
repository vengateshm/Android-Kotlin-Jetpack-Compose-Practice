package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton

data class ValidationError(val message: String)

data class FormFieldValidationResult(
    val hasError: Boolean = false,
    val errors: List<ValidationError> = emptyList()
)

interface FormFieldValidator {
    fun validate(text: String): FormFieldValidationResult
}
