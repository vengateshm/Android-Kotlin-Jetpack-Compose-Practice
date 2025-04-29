package dev.vengateshm.kotlin_practice.programs

import kotlin.math.pow

fun main() {
    println(isArmstrongNumber(153))
    println(isArmstrongNumber(123))
    println(isArmstrongNumber(153, 3))
    println(isArmstrongNumber(123, 3))
    println(isArmstrongNumberMath(153, 3))
    println(isArmstrongNumberMath(123, 3))
}

fun isArmstrongNumber(num: Int): Boolean {
    var result = 0
    var n = num
    while (n > 0) {
        val rem = n % 10
        result += rem * rem * rem
        n /= 10
    }
    return result == num
}

fun isArmstrongNumber(num: Int, k: Int): Boolean {
    var result = 0
    var n = num
    while (n > 0) {
        val rem = n % 10
        var product = 1
        repeat(k) {
            product *= rem
        }
        result += product
        n /= 10
    }
    return result == num
}

fun isArmstrongNumberMath(num: Int, pow: Int): Boolean {
    var result = 0
    var n = num
    while (n > 0) {
        val rem = n % 10
        result += rem.toDouble().pow(pow.toDouble()).toInt()
        n /= 10
    }
    return result == num
}