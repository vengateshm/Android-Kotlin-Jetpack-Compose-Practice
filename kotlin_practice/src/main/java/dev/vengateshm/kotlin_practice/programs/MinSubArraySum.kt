package dev.vengateshm.kotlin_practice.programs

import kotlin.math.min

fun main() {
    println(minSizeSubArraySum(intArrayOf(2, 3, 1, 2, 4, 3), 7))
}

fun minSizeSubArraySum(arr: IntArray, sum: Int): Int {
    var l = 0
    var h = 0
    var minLength = Int.MAX_VALUE
    var currentSum = 0
    while (h <= arr.lastIndex) {
        currentSum += arr[h]
        while (currentSum >= sum) {
            val currentLength = h - l + 1
            minLength = min(minLength, currentLength)
            currentSum = currentSum - arr[l]
            l++
        }
        h++
    }
    return minLength
}