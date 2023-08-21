package dev.vengateshm.kotlin_practice.programs

fun main() {
    isPerfectNumber(20)
}

fun isPerfectNumber(num: Int) {
    var sum = 0
    for (i in 1..num / 2) {
        if (num % i == 0) {
            sum += i
        }
    }
    if (sum == num)
        println("$num is a perfect number")
    else
        println("$num is not a perfect number")
}