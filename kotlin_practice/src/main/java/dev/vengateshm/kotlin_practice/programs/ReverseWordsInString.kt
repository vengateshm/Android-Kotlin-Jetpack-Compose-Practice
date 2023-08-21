package dev.vengateshm.kotlin_practice.programs

fun reverseWords(input: String): String {
    val words = input.split(" ")
    val reversedWords = words.map { word -> word.reversed() }
    return reversedWords.joinToString(" ")
}

fun reverseWords1(input: String): String {
    val words = mutableListOf<String>()
    val sb = StringBuilder()
    for (c in input) {
        if (c == ' ') {
            words.add(sb.toString())
            sb.clear()
        } else {
            sb.insert(0, c)
        }
    }
    words.add(sb.toString())
    val reversedWords = StringBuilder()
    for ((index, word) in words.withIndex()) {
        if (index > 0) {
            reversedWords.append(' ')
        }
        reversedWords.append(word)
    }

    return reversedWords.toString()
}

fun main() {
    val input = "Hello World Kotlin"
    val reversed = reverseWords1(input)
    println(reversed)
}