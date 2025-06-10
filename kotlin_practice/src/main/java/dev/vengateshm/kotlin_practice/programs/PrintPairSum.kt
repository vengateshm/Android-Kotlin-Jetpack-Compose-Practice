package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(getSumPairsFromTwoList(listOf(1, 2, 4, 5, 7), listOf(5, 6, 3, 4, 8), 9))
    println(
        getSumPairsFromThreeList(
            listOf(1, 2, 3, 4, 5),
            listOf(2, 3, 4, 5, 6),
            listOf(3, 4, 5, 6, 7),
            9,
        ),
    )
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

fun getSumPairsFromThreeList(
    nums1: List<Int>,
    nums2: List<Int>,
    nums3: List<Int>,
    target: Int,
): List<Triple<Int, Int, Int>> {
    val set1 = nums1.toHashSet()
    val set2 = nums2.toHashSet()
    val result = mutableListOf<Triple<Int, Int, Int>>()
    for (num3 in nums3) {
        val remainingSum = target - num3
        for (num1 in set1) {
            val complement = remainingSum - num1
            if (set2.contains(complement)) {
                result.add(Triple(num1, complement, num3))
            }
        }
    }
    return result
}