package dev.vengateshm.android_kotlin_compose_practice

import dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based.ErrorMessage
import dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based.PasswordRule
import dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based.PasswordValidation
import dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based.TextValidator
import org.junit.Assert
import org.junit.Test

class TextValidatorTest {
    @Test
    fun testFieldsValid() {
        val textValidator = TextValidator.Builder()
            .addRule(
                "1234Asdf", PasswordRule(
                    mapOf(
                        PasswordValidation.MIN_LENGTH to ErrorMessage.PasswordMinimumLength.msg,
                        PasswordValidation.MAX_LENGTH to ErrorMessage.PasswordMaximumLength.msg,
                        PasswordValidation.REQUIRES_DIGIT to ErrorMessage.PasswordOneDigit.msg,
                        PasswordValidation.REQUIRES_UPPERCASE to ErrorMessage.PasswordOneUpperCase.msg,
                        PasswordValidation.REQUIRES_LOWERCASE to ErrorMessage.PasswordOneLowerCase.msg,
                    )
                )
            )
            .build()

        Assert.assertTrue(textValidator.validate().first)
    }

    @Test
    fun testFieldsInValid() {
        val textValidator = TextValidator.Builder()
            .addRule(
                "1234A", PasswordRule(
                    mapOf(
                        PasswordValidation.MIN_LENGTH to ErrorMessage.PasswordMinimumLength.msg,
                        PasswordValidation.MAX_LENGTH to ErrorMessage.PasswordMaximumLength.msg,
                        PasswordValidation.REQUIRES_DIGIT to ErrorMessage.PasswordOneDigit.msg,
                        PasswordValidation.REQUIRES_UPPERCASE to ErrorMessage.PasswordOneUpperCase.msg,
                        PasswordValidation.REQUIRES_LOWERCASE to ErrorMessage.PasswordOneLowerCase.msg,
                    )
                )
            )
            .build()

        Assert.assertFalse(textValidator.validate().first)
    }
}