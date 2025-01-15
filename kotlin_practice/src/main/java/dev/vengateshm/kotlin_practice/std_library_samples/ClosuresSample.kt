package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    counter()
}

fun counter() {
    var count = 0
    val increment = { count++ }
    increment()
    increment()
    println(count)
}