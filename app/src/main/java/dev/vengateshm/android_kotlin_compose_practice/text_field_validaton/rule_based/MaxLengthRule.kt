package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based

class MaxLengthRule(private val maxLength: Int, error: String) : Rule(error) {
    override fun isValid(text: String): Boolean {
        return text.length <= maxLength
    }
}