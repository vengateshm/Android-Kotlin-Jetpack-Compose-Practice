package dev.vengateshm.kotlin_practice.programs.star_pattern

fun main() {
    val r = 5
    for (i in 0 until r) {
        for (k in 0 until r - 1 - i) {
            print(" ")
        }
        for (j in 0..i) {
            print(" *")
        }
        println()
    }
}