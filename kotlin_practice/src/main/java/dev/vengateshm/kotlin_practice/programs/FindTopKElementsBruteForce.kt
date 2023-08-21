package dev.vengateshm.kotlin_practice.programs

import kotlin.math.max

fun main() {
    println(findTopKElementsBruteForce(arrayOf(4, 8, 0, 2, 1), 2))
}

fun findTopKElementsBruteForce(arr: Array<Int>, k: Int): List<Int> {
    val arrayList = arr.toMutableList()
    val topElementsList = mutableListOf<Int>()

    for (i in 0 until k) { // No of times to find max
        var max = Int.MIN_VALUE
        for (j in 0 until arrayList.size) {
            max = max(arrayList[j], max)
        }
        arrayList.remove(max)
        topElementsList.add(max)
    }

    return topElementsList
}