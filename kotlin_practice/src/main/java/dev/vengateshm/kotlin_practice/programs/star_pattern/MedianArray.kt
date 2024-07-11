package dev.vengateshm.kotlin_practice.programs.star_pattern

fun main() {
    val arr1 = intArrayOf(20, 40, 30, 10, 50)
    val arr2 = intArrayOf(20, 40, 30, 10, 50, 60)

    println(findMedian(arr1))
    println(findMedian(arr2))
}

fun findMedian(arr: IntArray): Double {
    arr.sort()
    val n = arr.size
    return if (n % 2 == 0) {
        val mid1 = arr[n / 2]
        val mid2 = arr[(n - 1) / 2]
        (mid1 + mid2) / 2.0
    } else {
        arr[arr.size / 2].toDouble()
    }
}