package dev.vengateshm.kotlin_practice.programs

import kotlin.math.max

fun main() {
    println(largestUniqueNumber(intArrayOf(5, 7, 3, 9, 4, 9, 8, 3, 1)))
    println(largestUniqueNumber(intArrayOf(9, 9, 8, 8)))
}

fun largestUniqueNumber(arr: IntArray): Int {
    val frequencyMap = mutableMapOf<Int, Int>()
    var largestUnique = -1
    for (num in arr) {
        if (frequencyMap.containsKey(num)) {
            frequencyMap[num] = frequencyMap[num]!! + 1
        } else {
            frequencyMap[num] = 1
        }
    }
    for (key in frequencyMap.keys) {
        if (frequencyMap[key] == 1) {
            largestUnique = max(largestUnique, key)
        }
    }
    return largestUnique
}