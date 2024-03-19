package dev.vengateshm.kotlin_practice.regex

fun main() {
    val r1 = "[0-5]{3}".toRegex()
    println(r1.matches("127"))
    println(r1.matches("125"))
    println(r1.matches("000"))
    println(r1.matches("555"))

    println()

    val r2 = "\\d{2}".toRegex()
    println(r2.matches("00"))
    println(r2.matches("22"))
    println(r2.matches("09"))
    println(r2.matches("10"))
    println(r2.matches("100"))

    println()

    val r3 = "^(-?\\d+\\.)?-?\\d+$".toRegex()
    println("".matches(r3))
    println("1".matches(r3))
    println("-1".matches(r3))
    println("1992".matches(r3))
    println("-1992".matches(r3))
    println("-.5".matches(r3))
    println("0.5".matches(r3))
    println("-0.5".matches(r3))
    println("100.16".matches(r3))
    println("-100.16".matches(r3))
}