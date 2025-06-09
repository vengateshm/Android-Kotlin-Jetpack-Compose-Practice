package dev.vengateshm.kotlin_practice.programs

class NumArray(nums: IntArray) {
    val prefixSum = IntArray(nums.size + 1)

    init {
        for (i in nums.indices) {
            prefixSum[i + 1] = prefixSum[i] + nums[i]
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        return prefixSum[right + 1] - prefixSum[left]
    }
}

fun main() {
    val obj = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))
    println(obj.sumRange(0, 2))  // Output: 1
    println(obj.sumRange(2, 5))  // Output: -1
    println(obj.sumRange(0, 5))  // Output: -3
}