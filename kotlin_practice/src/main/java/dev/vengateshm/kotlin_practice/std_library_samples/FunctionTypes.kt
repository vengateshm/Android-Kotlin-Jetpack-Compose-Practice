package dev.vengateshm.kotlin_practice.std_library_samples

import kotlin.reflect.KFunction1
import kotlin.reflect.KMutableProperty0

fun timesTwo(it: Int) = { it * 2 }

// Reflection types
fun main() {
    val twice: KFunction1<Int, () -> Int> = ::timesTwo
    val quantity = Quantity(12)
    val property: KMutableProperty0<Int> = quantity::value
}

class Quantity(var value: Int)