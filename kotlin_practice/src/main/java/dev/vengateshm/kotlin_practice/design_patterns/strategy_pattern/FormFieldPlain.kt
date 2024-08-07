package dev.vengateshm.kotlin_practice.design_patterns.strategy_pattern

interface FormFieldPlain {
    val name: String
    val value: String
    fun isValid(): Boolean
}

class EmailFieldPlain(override val value: String) : FormFieldPlain {
    override val name = "email"
    override fun isValid() = value.contains("@") && value.contains(".")
}

class UsernameFieldPlain(override val value: String) : FormFieldPlain {
    override val name = "username"
    override fun isValid() = value.isNotBlank()
}

class PasswordFieldPlain(override val value: String) : FormFieldPlain {
    override val name = "password"
    override fun isValid() = value.length >= 8
}

class OptionalEmailFieldPlain(override val value: String = "") : FormFieldPlain {
    override val name = "email"
    override fun isValid() = value.isEmpty() || value.contains("@") && value.contains(".")
}

class OptionalUsernameFieldPlain(override val value: String = "") : FormFieldPlain {
    override val name = "username"
    override fun isValid() = value.isEmpty() || value.isNotBlank()
}

class OptionalPasswordFieldPlain(override val value: String = "") : FormFieldPlain {
    override val name = "password"
    override fun isValid() = value.isEmpty() || value.length >= 8
}