package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(
        eraseOverlapIntervals(
            arrayOf(
                intArrayOf(7, 9),
                intArrayOf(9, 11),
                intArrayOf(11, 15),
                intArrayOf(8, 12),
            ),
        ),
    )
    println(
        eraseOverlapIntervals1(
            arrayOf(
                intArrayOf(7, 9),
                intArrayOf(9, 11),
                intArrayOf(11, 15),
                intArrayOf(8, 12),
            ),
        ),
    )
}

// Sort by upper bound O(nlogn)
fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
    if (intervals.isEmpty()) return 0
    intervals.sortBy { it[1] }
    var removed = 0
    var prevEnd = intervals[0][1]
    for (i in 1 until intervals.size) {
        if (intervals[i][0] < prevEnd) {
            removed++
        } else {
            prevEnd = intervals[i][1]
        }
    }
    println(intervals.contentDeepToString())
    return removed
}

// Sort by lower bound
fun eraseOverlapIntervals1(intervals: Array<IntArray>): Int {
    if (intervals.isEmpty()) return 0
    intervals.sortBy { it[0] }
    var removed = 0
    var prevEnd = intervals[0][1]
    for (i in 1 until intervals.size) {
        if (intervals[i][0] < prevEnd) {
            removed++
            prevEnd = minOf(prevEnd, intervals[i][1])
        } else {
            prevEnd = intervals[i][1]
        }
    }
    println(intervals.contentDeepToString())
    return removed
}