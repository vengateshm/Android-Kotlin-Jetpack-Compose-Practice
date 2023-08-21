package dev.vengateshm.kotlin_practice.programs

import java.util.PriorityQueue
import java.util.stream.Collectors

fun main() {
    println(findTopKElementsPriorityQueue(arrayOf(4, 8, 0, 2, 1), 2))
    println(findBottomKElementsPriorityQueue(arrayOf(4, 8, 0, 2, 1), 2))
}

fun findTopKElementsPriorityQueue(array: Array<Int>, k: Int): List<Int> {
    val maxHeap = PriorityQueue<Int>()
    // By default elements added in natural sorting order
    // poll() removes item from front
    for (i in array.indices) {
        maxHeap.add(array[i])
        if (maxHeap.size > k) {
            maxHeap.poll()
        }
    }

    return maxHeap.sortedByDescending { it }
}

fun findBottomKElementsPriorityQueue(array: Array<Int>, k: Int): List<Int> {
    val minHeap = PriorityQueue<Int>()
    // By default elements added in natural sorting order
    // poll() removes item from front
    for (i in array.indices) {
        minHeap.add(array[i])
        if (minHeap.size > k) {
            minHeap.poll()
        }
    }

    return minHeap.stream().collect(Collectors.toList())
}