package dev.vengateshm.kotlin_practice.programs

import kotlin.random.Random

fun main() {
    println(generateRandomNumberBetween(5, 10))
}

fun generateRandomNumberBetween(min: Int, max: Int): Int {
    // + min - if minimum value is non zero
    return (Random.nextDouble() * (max - min + 1) + min).toInt()
}