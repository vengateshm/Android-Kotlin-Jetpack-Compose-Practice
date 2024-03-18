package dev.vengateshm.kotlin_practice.programs.star_pattern

fun main() {
    val word = "COMPUTER"
    val length = word.length
    for (i in 0 until length) {
        for (j in 0..i) {
            print(word[j])
        }
        println()
    }
}