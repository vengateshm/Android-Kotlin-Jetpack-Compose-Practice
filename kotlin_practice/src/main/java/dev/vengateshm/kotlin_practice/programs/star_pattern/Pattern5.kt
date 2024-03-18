package dev.vengateshm.kotlin_practice.programs.star_pattern

fun main() {
    val r = 5
    for (i in 0 until r) {
        for (k in 0 until i) {
            print(" ")
        }
        for (j in (r - 1) downTo i step 1) {
            print("*")
        }
        println()
    }
}