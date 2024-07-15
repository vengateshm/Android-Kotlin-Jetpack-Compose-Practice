package dev.vengateshm.kotlin_practice.programs

fun main() {
    //println(isHappy(19))
    println(isHappy(2))
}

fun isHappy(num: Int): Boolean {
    var n = num
    val seen = mutableSetOf<Int>()

    while (n > 0 && n !in seen) {
        seen.add(n)
        n = getNextNumber(n)
    }

    return n == 1
}

fun getNextNumber(num: Int): Int {

    var n = num
    var sum = 0
    while (n > 0) {
        val digit = n % 10
        sum += digit * digit
        n /= 10
    }
    return sum
}