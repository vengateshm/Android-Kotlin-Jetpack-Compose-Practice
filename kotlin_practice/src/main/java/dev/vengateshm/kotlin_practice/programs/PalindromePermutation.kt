package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(canFormAPalindrome("code"))
    println(canFormAPalindrome("aab"))
    println(canFormAPalindrome("carerac"))
}

fun canFormAPalindrome(str: String): Boolean {
    return str.groupBy { it }.values.filter { it.size % 2 == 1 }.size <= 1
}