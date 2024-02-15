package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton.rule_based

class TextValidator(private val builder: Builder) {

    fun validate(): Pair<Boolean, String> {
        for (text in builder.rules.keys) {
            val rule = builder.rules[text]!!
            if (!rule.isValid(text)) {
                return Pair(false, rule.error)
            }
        }
        return Pair(true, "")
    }

    class Builder {

        val rules = mutableMapOf<String, Rule>()

        fun addRule(text: String, rule: Rule) = apply {
            rules[text] = rule
        }

        fun build(): TextValidator {
            return TextValidator(this@Builder)
        }
    }
}