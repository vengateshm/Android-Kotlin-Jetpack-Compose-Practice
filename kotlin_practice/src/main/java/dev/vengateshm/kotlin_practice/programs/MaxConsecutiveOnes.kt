package dev.vengateshm.kotlin_practice.programs

import kotlin.math.max

fun main() {
    println(maxConsecutiveOnes(intArrayOf(1, 1, 0, 1, 1, 1)))
    println(maxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0, 1)))
    println(maxConsecutiveOnes(intArrayOf(1, 1, 1, 1, 1)))
    println(maxConsecutiveOnes(intArrayOf(0, 0, 0, 0, 0)))
    println(maxConsecutiveOnes(intArrayOf(1)))
    println(maxConsecutiveOnes(intArrayOf(0)))
    println(maxConsecutiveOnes(intArrayOf(0, 1)))
}

fun maxConsecutiveOnes(nums: IntArray): Int {
    var count = 0
    var max = Int.MIN_VALUE
    for (num in nums) {
        if (num == 1) {
            count++
            max = max(count, max)
        } else {
            count = 0
        }
    }
    return if (max == Int.MIN_VALUE) 0 else max
}