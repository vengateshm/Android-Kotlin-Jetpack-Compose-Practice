package dev.vengateshm.kotlin_practice.std_library_samples

class RegexStringExtensions {
    // Regex
    // + - 1 or more
    // * - 0 or more
    // . - any
    // ^ - not or negation

    // String contains at least one number
    fun String.containsNumber(): Boolean {
        val pattern = ".*\\d+.*"
        return Regex(pattern).matches(this)
    }

    // String contains at least one lowercase
    fun String.containsLowercase(): Boolean {
        val pattern = ".*[a-z]+.*"
        return Regex(pattern).matches(this)
    }

    // String contains at least one uppercase
    fun String.containsUppercase(): Boolean {
        val pattern = ".*[A-Z]+.*"
        return Regex(pattern).matches(this)
    }

    // String contains at least one special characters
    fun String.containsSpecialCharacter(): Boolean {
        val pattern = ".*[^A-Za-z\\d]+.*"
        return Regex(pattern).matches(this)
    }
}

fun main() {
    val extensions = RegexStringExtensions()
    with(extensions) {
        println("NoNumber".containsNumber())
        println("200Number".containsNumber())
        println("Num-1ber".containsNumber())

        println()

        println("100".containsLowercase())
        println("100a".containsLowercase())
        println("a100".containsLowercase())
        println("10c0".containsLowercase())

        println()

        println("100".containsUppercase())
        println("100D".containsUppercase())
        println("A100".containsUppercase())
        println("10A0".containsUppercase())

        println()

        println("100".containsSpecialCharacter())
        println("100^".containsSpecialCharacter())
        println("$100".containsSpecialCharacter())
        println("10@0".containsSpecialCharacter())
    }
}