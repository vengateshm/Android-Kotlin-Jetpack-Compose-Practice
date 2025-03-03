package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(canReachLastIndex(intArrayOf(2, 3, 1, 1, 4)))
    println(canReachLastIndex(intArrayOf(3, 2, 1, 0, 4)))
    println(canReachLastIndex(intArrayOf(2, 3, 1, 0, 4)))

    println(canJump(intArrayOf(2, 3, 1, 1, 4)))
    println(canJump(intArrayOf(3, 2, 1, 0, 4)))
    println(canJump(intArrayOf(2, 3, 1, 0, 4)))
}

fun canReachLastIndex(arr: IntArray): Boolean {
    var lastPos = arr.size - 1
    for (i in arr.size - 2 downTo 0) {
        if (i + arr[i] >= lastPos) {
            lastPos = i
        }
    }
    return lastPos == 0
}

fun canJump(nums: IntArray): Boolean {
    var maxReach = 0

    for (i in nums.indices) {
        // If current position is beyond our maximum reach, we can't get here
        if (i > maxReach) {
            return false
        }

        // Update the maximum reach from current position
        maxReach = maxOf(maxReach, i + nums[i])

        // If we can already reach the last index, return true
        if (maxReach >= nums.size - 1) {
            return true
        }
    }

    // If we've gone through the entire array without returning,
    // it means we can't reach the end
    return false
}