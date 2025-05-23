package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(isPangram1("The quick brown fox jumps over the lazy dog"))
    println(isPangram1("The quick brown fox jumps over the lazy dog and cats"))
    println(isPangram2("The quick brown fox jumps over the lazy dog"))
    println(isPangram2("The quick brown fox jumps over the lazy dog and cats"))
    println(isPangram2("The quick brown fox jumps over the lazy dog"))
    println(isPangram2("The quick brown fox jumps over the lazy dog and cats"))
    println(isPangram3("The quick brown fox jumps over"))
    println(isPangramNaive("The quick brown fox jumps over"))
    println(isPangramNaive("The quick brown fox jumps over the lazy dog"))
}

fun isPangram1(input: String): Boolean {
    val alphabets = 'a'..'z'
    val processedInput = input.lowercase().toSet()
    return alphabets.all { it in processedInput }
}

fun isPangram2(input: String): Boolean {
    return input
        .lowercase()
        .filter { it in 'a'..'z' }
        .toSet()
        .size == 26
}

fun isPangram3(input: String): Boolean {
    val alphabets = ('a'..'z').toSet()
    val processedInput = input.lowercase().filter { it in 'a'..'z' }.toSet()
    val missing = alphabets - processedInput

    if (missing.isEmpty()) {
        println("Pangram!")
    } else {
        println("Not a pangram. Missing letters: ${missing.joinToString(", ")}")
    }
    return missing.isEmpty()
}

fun isPangramNaive(input: String): Boolean {
    val lowerInput = input.lowercase()

    for (ch in 'a'..'z') {
        if (ch !in lowerInput) {
            return false // As soon as one letter is missing, return false
        }
    }
    return true // All letters found
}