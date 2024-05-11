package dev.vengateshm.kotlin_practice.grouping

fun main() {
    // Sock
    // 1 - color
    // 2 - color
    // 3 - color

    // Find color pairs
    val basket = arrayOf(1, 2, 1, 2, 1, 3, 2)

    val grouped = basket.groupBy { it }
    val pairs = grouped.values.sumOf { it.size / 2 }
    println(grouped)
    println(pairs)
}