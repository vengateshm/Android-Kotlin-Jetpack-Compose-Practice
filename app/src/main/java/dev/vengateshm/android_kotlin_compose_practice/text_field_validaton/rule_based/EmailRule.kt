package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based

import android.util.Patterns

class EmailRule(error: String) : Rule(error) {
    override fun isValid(text: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
}