package dev.vengateshm.kotlin_practice.programs.star_pattern

fun main() {
    val r = 8
    for (i in 0 until (r * 2) - 1) {
        if (i < r) {
            for (j in 0..i) {
                print("*")
            }
        } else {
            for (j in (r - 2) downTo i - r) {
                print("*")
            }
        }
        println()
    }
}