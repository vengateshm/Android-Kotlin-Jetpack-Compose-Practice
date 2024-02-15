package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based

abstract class Rule(internal var error: String) {
    abstract fun isValid(text: String): Boolean
}