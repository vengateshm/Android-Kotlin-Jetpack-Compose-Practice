package dev.vengateshm.kotlin_practice.programs.star_pattern

fun main() {
    val r = 3
    for (i in 0 until r) {
        for (j in (r - 1) downTo i) {
            print("*")
        }
        println()
    }
}