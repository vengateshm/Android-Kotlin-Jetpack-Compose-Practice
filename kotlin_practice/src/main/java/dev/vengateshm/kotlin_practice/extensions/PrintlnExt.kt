package dev.vengateshm.kotlin_practice.extensions

fun Any.println(): Any {
    println(this)
    return this
}

fun <T> T.print(): T {
    println(this)
    return this
}

fun main() {
    1.println()
    (0..10).println()
    (5 until 10).println()

    (3..8).print()
}