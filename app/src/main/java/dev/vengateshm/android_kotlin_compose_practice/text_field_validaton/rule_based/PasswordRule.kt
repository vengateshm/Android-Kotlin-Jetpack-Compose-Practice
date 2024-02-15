package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based

class PasswordRule(private val rules: Map<PasswordValidation, String>) : Rule("") {
    override fun isValid(text: String): Boolean {
        for (rule in rules.keys) {
            val errorMsg = rules[rule] ?: "Unknown error"
            when (rule) {
                PasswordValidation.MIN_LENGTH -> {
                    if (text.length < PASSWORD_MIN_LENGTH) {
                        error = errorMsg
                        return false
                    }
                }

                PasswordValidation.MAX_LENGTH -> {
                    if (text.length > PASSWORD_MAX_LENGTH) {
                        error = errorMsg
                        return false
                    }
                }

                PasswordValidation.REQUIRES_UPPERCASE -> {
                    if (!containsUppercase(text)) {
                        error = errorMsg
                        return false
                    }
                }

                PasswordValidation.REQUIRES_LOWERCASE -> {
                    if (!containsLowercase(text)) {
                        error = errorMsg
                        return false
                    }
                }

                PasswordValidation.REQUIRES_DIGIT -> {
                    if (!containsDigit(text)) {
                        error = errorMsg
                        return false
                    }
                }
            }
        }
        return true
    }

    private fun containsUppercase(text: String): Boolean {
        for (char in text) {
            if (char.isUpperCase()) {
                return true
            }
        }
        return false
    }

    private fun containsLowercase(text: String): Boolean {
        for (char in text) {
            if (char.isLowerCase()) {
                return true
            }
        }
        return false
    }

    private fun containsDigit(text: String): Boolean {
        for (char in text) {
            if (char.isDigit()) {
                return true
            }
        }
        return false
    }

    companion object {
        const val PASSWORD_MIN_LENGTH = 8
        const val PASSWORD_MAX_LENGTH = 15
    }
}

enum class PasswordValidation {
    MIN_LENGTH,
    MAX_LENGTH,
    REQUIRES_LOWERCASE,
    REQUIRES_UPPERCASE,
    REQUIRES_DIGIT
}