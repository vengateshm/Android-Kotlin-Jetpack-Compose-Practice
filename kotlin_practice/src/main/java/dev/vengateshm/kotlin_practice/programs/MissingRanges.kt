package dev.vengateshm.kotlin_practice.programs

fun main() {
    missingRange2(0, 99, arrayOf(0, 1, 3, 50, 75)).forEach { println(it) }
    missingRange2(-1, -1, arrayOf(-1)).forEach { println(it) }
}

// 0 1 3 50 75
// 0 1 2 3 4 5 ...
fun missingRange(lower: Int, upper: Int, nums: Array<Int>): List<Pair<Int, Int>> {
    val ranges = mutableListOf<Pair<Int, Int>>()
    var start = -1
    for (i in lower..upper + 1) {
        if (i in lower..upper && nums.contains(i).not()) {
            if (start == -1) {
                start = i
            }
        } else {
            if (start != -1) {
                ranges.add(start to i - 1)
                start = -1
            }
        }
    }
    return ranges
}

fun missingRange1(lower: Int, upper: Int, nums: Array<Int>): List<Pair<Int, Int>> {
    val ranges = mutableListOf<Pair<Int, Int>>()
    var start = -1
    for (i in lower..upper + 1) {
        if ((i <= upper) && nums.contains(i).not()) {
            if (start == -1) {
                start = i
            }
        } else {
            if (start != -1) {
                ranges.add(start to i - 1)
                start = -1
            }
        }
    }
    return ranges
}

fun missingRange2(lower: Int, upper: Int, nums: Array<Int>): List<Pair<Int, Int>> {
    val ranges = mutableListOf<Pair<Int, Int>>()
    var nextNum = lower
    for (i in nums.indices) {
        if (nextNum == nums[i]) {
            nextNum = nextNum + 1
            continue
        } else {
            ranges.add(Pair(nextNum, nums[i] - 1))
            nextNum = nums[i] + 1
        }
    }
    if (nextNum < upper) {
        ranges.add(Pair(nextNum, upper))
    }
    return ranges
}