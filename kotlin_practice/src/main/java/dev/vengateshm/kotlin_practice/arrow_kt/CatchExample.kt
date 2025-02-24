package dev.vengateshm.kotlin_practice.arrow_kt

import arrow.core.Either.Companion.catch

fun main() {
    val result = catch {
        getCatchData(1)
    }
    result.fold(
        ifLeft = { println("Error: $it") },
        ifRight = { println("Success: $it") },
    )
}

fun getCatchData(value: Int): String {
    require(value > 0) { "Value must be positive" }
    return value.toString()
}