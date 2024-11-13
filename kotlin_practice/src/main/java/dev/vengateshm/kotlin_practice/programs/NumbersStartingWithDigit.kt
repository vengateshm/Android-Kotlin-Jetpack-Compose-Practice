package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(numbersStartingWithDigit(intArrayOf(1, 23, 123, 167)))
}

fun numbersStartingWithDigit(array: IntArray): List<Int> {
    val result = mutableListOf<Int>()
    for (number in array) {
        if (getFirstDigit(number) == 2) {
            result.add(number)
        }
    }
    return result
}

fun getFirstDigit(number: Int): Int {
    var temp = number
    while (temp >= 10) {
        temp /= 10
    }
    return temp
}