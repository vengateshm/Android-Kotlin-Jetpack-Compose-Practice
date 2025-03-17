package dev.vengateshm.kotlin_practice.programs

import java.util.TreeSet

fun main() {
    println(findLongestConsecutiveSequenceInArray(intArrayOf(100, 1, 200, 2, 4, 3)))
}

fun findLongestConsecutiveSequenceInArray(arr: IntArray): Int {
    val sortedSet = TreeSet(arr.toSet())

    var maxLength = Int.MIN_VALUE
    var prev = Int.MIN_VALUE
    var currentLength = 0
    for (num in sortedSet) {
        if (num == prev + 1) {
            currentLength++
        } else {
            currentLength = 1
        }
        maxLength = maxOf(maxLength, currentLength)
        prev = num
    }

    return maxLength
}