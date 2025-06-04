package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(maxSumOfMinPair(intArrayOf(1, 4, 3, 2)))
    println(maxSumOfMinPair(intArrayOf(6, 2, 6, 5, 1, 2)))
    generateAllPairs(intArrayOf(1, 4, 3, 2))
    println(arrayPairSum(intArrayOf(1, 4, 3, 2)))
    println(arrayPairSum(intArrayOf(6, 2, 6, 5, 1, 2)))
}

fun maxSumOfMinPair(nums: IntArray): Int {
    nums.sort() // 1, 2, 3, 4
    // 1,2,2,5,6,6
    var sum = 0
    for (i in nums.indices step 2) {
        sum += nums[i] // min of the pair (nums[i], nums[i+1])
    }
    return sum
}

fun arrayPairSum(nums: IntArray): Int {
    return nums.sorted()                  // Sort the array, returns a List<Int>
        .chunked(2) { it.minOrNull()!! } // Split into pairs of 2, get min of each pair
        .sum()                           // Sum all those mins
}

fun generateAllPairs(nums: IntArray) {
    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in nums.indices) {
        for (j in i + 1 until nums.size) {
            pairs.add(Pair(nums[i], nums[j]))
        }
    }
    println(pairs)
}

