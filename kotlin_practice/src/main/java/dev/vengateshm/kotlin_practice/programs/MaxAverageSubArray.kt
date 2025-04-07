package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(maxAverageSubArray(intArrayOf(1, 12, -5, -6, 50, 3), 4))
    println(maxAverageSubArray(intArrayOf(5), 1))
    println(maxAverageSubArray(intArrayOf(5, 4, 9), 1))
}

fun maxAverageSubArray(arr: IntArray, k: Int): Double {
    var maxSum = 0
    var currentSum = 0
    for (i in 0 until k) {
        currentSum += arr[i]
    }
    maxSum = currentSum
    for (i in k until arr.size) {
        currentSum += arr[i] - arr[i - k]
        maxSum = maxOf(currentSum, maxSum)
    }

    return maxSum.toDouble() / k
}