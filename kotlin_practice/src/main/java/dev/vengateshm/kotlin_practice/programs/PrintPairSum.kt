package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(getSumPairsFromTwoList(listOf(1, 2, 4, 5, 7), listOf(5, 6, 3, 4, 8), 9))
}

fun getSumPairsFromTwoList(nums1: List<Int>, nums2: List<Int>, target: Int): List<Pair<Int, Int>> {
    val set = nums1.toHashSet()
    val result = mutableListOf<Pair<Int, Int>>()
    for (num in nums2) {
        val complement = target - num
        if (set.contains(complement)) {
            result.add(Pair(complement, num))
        }
    }
    return result
}