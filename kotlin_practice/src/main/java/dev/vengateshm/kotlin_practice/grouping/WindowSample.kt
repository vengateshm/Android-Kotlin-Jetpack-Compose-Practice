package dev.vengateshm.kotlin_practice.grouping

fun main() {
    // 2, 2, 1, 3, 2
    // 2 continuous bar
    // numbers in bar equal to 4
    println(birthDay(arrayOf(2, 2, 1, 3, 2), 4, 2))
}

fun birthDay(a: Array<Int>, d: Int, m: Int): Int {
    return a.asSequence().windowed(m).map { it.sum() }.count { it == d }
}