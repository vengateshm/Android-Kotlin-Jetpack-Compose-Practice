package dev.vengateshm.kotlin_practice.programs.star_pattern

fun main() {
    val r = 3
    for (i in 0 until r) {
        for (j in 0..i) {
            print("*")
        }
        println()
    }
}