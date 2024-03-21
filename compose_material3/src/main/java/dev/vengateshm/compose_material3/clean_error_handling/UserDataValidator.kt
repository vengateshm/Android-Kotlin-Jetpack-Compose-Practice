package dev.vengateshm.compose_material3.clean_error_handling

class UserDataValidator {
    fun validatePassword(password: String): Result<Unit, PasswordError> {
        if (password.length < 9)
            return Result.Error(PasswordError.TOO_SHORT)
        return Result.Success(Unit)
    }

    enum class PasswordError : Error {
        TOO_SHORT,
        NO_UPPERCASE,
        NO_DIGIT
    }
}