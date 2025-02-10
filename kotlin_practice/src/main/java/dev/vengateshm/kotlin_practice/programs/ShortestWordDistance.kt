package dev.vengateshm.kotlin_practice.programs

import kotlin.math.abs

fun main() {
    println(
        shortestWordDistance(
            arrayOf("practice", "makes", "perfect", "coding", "makes"),
            "coding",
            "practice",
        ),
    )
    println(
        shortestWordDistance(
            arrayOf("practice", "makes", "perfect", "coding", "makes"),
            "makes",
            "coding",
        ),
    )
}

fun shortestWordDistance(words: Array<String>, word1: String, word2: String): Int {
    var word1Index = -1
    var word2Index = -1
    var minDistance = Int.MAX_VALUE
    for (i in words.indices) {
        if (words[i] == word1) {
            word1Index = i
        } else if (words[i] == word2) {
            word2Index = i
        }
        if (word1Index >= 0 && word2Index >= 0) {
            minDistance = minOf(minDistance, abs(word1Index - word2Index))
        }
    }
    return minDistance
}