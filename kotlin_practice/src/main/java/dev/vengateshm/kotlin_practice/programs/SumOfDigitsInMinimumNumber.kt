package dev.vengateshm.kotlin_practice.programs

fun main() {
    val r1 = sumOfDigitsInMinimumNumber(intArrayOf(34, 23, 1, 24, 75, 33, 54, 8))
    println(validate(r1))
    val r2 = sumOfDigitsInMinimumNumber(intArrayOf(99, 77, 33, 66, 55))
    println(validate(r2))
}

fun sumOfDigitsInMinimumNumber(arr: IntArray): Int {
    var min = Int.MAX_VALUE
    for (i in arr.indices) {
        val num = arr[i]
        if (num < min) {
            min = num
        }
    }
    var sum = 0
    while (min > 0) {
        sum += min % 10
        min /= 10
    }
    return sum
}

fun validate(num: Int): Int {
    val isOdd = num % 2 != 0
    return if (isOdd) return 0 else 1
}