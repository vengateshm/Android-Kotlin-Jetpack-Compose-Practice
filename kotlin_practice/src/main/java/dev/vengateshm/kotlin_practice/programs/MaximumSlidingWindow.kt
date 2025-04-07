package dev.vengateshm.kotlin_practice.programs

import java.util.Arrays
import java.util.PriorityQueue
import kotlin.math.max

fun main() {
    println(maximumSlidingWindowBruteForce(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3))
    println(maximumSlidingWindowBruteForce(intArrayOf(3, 5, -1, 2, 6, -5, 7, 1), 3))
    println(
        maximumSlidingWindowOptimized1(
            intArrayOf(3, 5, -1, 2, 6, -5, 7, 1),
            3,
        ).contentToString(),
    )
    println(
        maximumSlidingWindowOptimized2(
            intArrayOf(3, 5, -1, 2, 6, -5, 7, 1),
            3,
        ).contentToString(),
    )
    println(
        maximumSlidingWindowOptimized3(
            intArrayOf(3, 5, -1, 2, 6, -5, 7, 1),
            3,
        ).contentToString(),
    )
}

fun maximumSlidingWindowBruteForce(arr: IntArray, k: Int): MutableList<Int> {
    if (k == 1) return arr.toMutableList()
    var result = mutableListOf<Int>()
    for (i in 0..arr.size - k) {
        var max = Int.MIN_VALUE
        for (j in i until i + k) {
            max = maxOf(max, arr[j])
        }
        result.add(max)
    }
    return result
}

fun maximumSlidingWindowOptimized1(arr: IntArray, k: Int): Array<Int> {
    if (k == 1) return arr.toTypedArray()
    var maxOfSubArray = Array<Int>(arr.size - k + 1) { 0 }
    for (i in 0..arr.size - k) {
        if (i > 0 && maxOfSubArray[i - 1] != arr[i - 1]) {
            maxOfSubArray[i] = max(maxOfSubArray[i - 1], arr[i + k - 1])
        } else {
            var max = Int.MIN_VALUE
            for (j in i until i + k) {
                max = maxOf(max, arr[j])
            }
            maxOfSubArray[i] = max
        }
    }
    return maxOfSubArray
}

//3, 5, -1, 2, 6, -5, 7, 1
fun maximumSlidingWindowOptimized2(arr: IntArray, k: Int): Array<Int> {
    if (k == 1) return arr.toTypedArray()
    var maxOfSubArray = Array<Int>(arr.size - k + 1) { 0 }
    val deque = ArrayDeque<Int>()
    var resIndex = 0
    for (i in 0 until arr.size) {
        if (deque.isNotEmpty() && deque.first() == i - k) {
            deque.removeFirst()
        }
        while (deque.isNotEmpty() && arr[deque.last()] < arr[i]) {
            deque.removeLast()
        }
        deque.addLast(i)
        if (i >= k - 1) {
            maxOfSubArray[resIndex++] = arr[deque.first()]
        }
    }
    return maxOfSubArray
}

fun maximumSlidingWindowOptimized3(arr: IntArray, k: Int): Array<Int> {
    if (k == 1) return arr.toTypedArray()
    var maxOfSubArray = Array<Int>(arr.size - k + 1) { 0 }
    val queue = PriorityQueue<Int>(Comparator.reverseOrder<Int>())

    Arrays.stream(arr, 0, k).forEach(queue::add)
    maxOfSubArray[0] = queue.peek()

    for (i in k until arr.size) {
        queue.remove(arr[i - k])
        queue.add(arr[i])
        maxOfSubArray[i - k + 1] = queue.peek()
    }

    return maxOfSubArray
}