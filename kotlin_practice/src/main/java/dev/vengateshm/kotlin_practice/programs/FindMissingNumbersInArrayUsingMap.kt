package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(findMissingNumberInArray(intArrayOf(1, 2, 3, 5, 6, 7, 8, 9, 10), 10))
    println(findMissingNumberInArray(intArrayOf(1, 2, 8, 9), 9))
}

fun findMissingNumberInArray(arr: IntArray, n: Int): List<Int> {
    val missingNumbers = mutableListOf<Int>()

    val mapped = arr.associateBy({ it }, { true })
    for (i in 1..n) {
        if (!mapped.containsKey(i)) missingNumbers.add(i)
    }
    return missingNumbers
}