package dev.vengateshm.kotlin_practice.programs.star_pattern

fun main() {
    val c = 65
    val r = 5
    for (i in 0 until r) {
        for (j in 0..i) {
            print((c + j).toChar())
        }
        println()
    }
}