package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based

class ZipCodeRule(error: String) : Rule(error) {
    override fun isValid(text: String): Boolean {
        return text.matches(Regex(ZIPCODE_REGEX))
    }

    companion object {
        const val ZIPCODE_REGEX = "^\\d{5}(-\\d{4})?$"
    }
}