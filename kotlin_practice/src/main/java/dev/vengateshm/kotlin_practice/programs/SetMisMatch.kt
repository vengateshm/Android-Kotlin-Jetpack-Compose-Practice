package dev.vengateshm.kotlin_practice.programs

import kotlinx.collections.immutable.toImmutableList
import kotlin.math.abs

fun main() {
    println(setMisMatch(intArrayOf(1, 2, 2, 4)))
    println(setMisMatch(intArrayOf(1, 1)))
    println(setMisMatch1(intArrayOf(1, 2, 2, 4)))
    println(setMisMatch1(intArrayOf(1, 1)))
}

// Here extra space used
fun setMisMatch(nums: IntArray): List<Int> {
    val result = mutableListOf<Int>()

    val numsMap = nums.toList().groupingBy { it }.eachCount()

    for (i in 1..nums.size) {
        if (i in numsMap) {
            if (numsMap[i] == 2)
                result.add(i)
        } else {
            result.add(i)
        }
    }

    return result.toImmutableList()
}

// 1, 2, 2, 4
fun setMisMatch1(nums: IntArray): List<Int> {
    val result = mutableListOf<Int>()

    for (num in nums) {
        if (nums[abs(num) - 1] < 0) {
            result.add(abs(num))
        } else {
            nums[abs(num) - 1] = nums[abs(num) - 1] * -1
        }
    }

    for (i in 0 until nums.size) {
        if (nums[i] > 0)
            result.add(i + 1)
    }

    return result.toImmutableList()
}