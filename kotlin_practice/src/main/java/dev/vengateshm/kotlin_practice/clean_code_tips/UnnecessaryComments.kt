package dev.vengateshm.kotlin_practice.clean_code_tips

import kotlin.math.roundToInt
import kotlin.math.sqrt

// With unnecessary comments
fun isPrime1(number: Int): Boolean {
    // Check if number is less than 2
    if (number < 2) {
        // If less than 2, not a prime number
        return false
    }

    // At least 1 divisor must be less than square root, so return false
    for (i in 2 until sqrt(number.toDouble()).roundToInt()) {
        // Check if number is divisible by i
        if (number % i == 0)
        // If divisible, number is not prime
            return false
    }

    // After all checks, if not divisible by any i, number is prime
    return true
}

// With only necessary comments
fun isPrime(number: Int): Boolean {
    if (number < 2) {
        return false
    }
    // At least 1 divisor must be less than square root, so return false
    for (i in 2 until sqrt(number.toDouble()).roundToInt()) {
        if (number % i == 0) {
            return false
        }
    }

    return true
}


fun main() {
    isPrime1(1)
    isPrime(1)
}