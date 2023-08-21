package dev.vengateshm.kotlin_practice.programs

fun main() {
    val str = "\$jav@a!17"
    val str1 = "\$jav@a!17"
    println(str.replace("[^A-Za-z]".toRegex(), ""))
    println(str1.replace("[^A-Za-z0-9]".toRegex(), ""))
}