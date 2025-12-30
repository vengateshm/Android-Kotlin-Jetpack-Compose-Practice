package dev.vengateshm.kotlin_practice.programs

import java.util.Collections
import java.util.PriorityQueue

class MedianFinder {
  var even = true // 0 elements in list initially
  val list = mutableListOf<Int>()

  fun addNum(num: Int) {
    list.add(num)
    even = !even
  }

  fun median(): Double {
    list.sort()
    val size = list.size
    val result = if (even) {
      (list[(size / 2) - 1] + list[size / 2]) / 2.0
    } else {
      (list[size / 2]).toDouble()
    }
    return result
  }
}

fun main() {
  val medianFinder = MedianFinder()
  medianFinder.addNum(1)
  medianFinder.addNum(6)
  medianFinder.addNum(5)
  medianFinder.addNum(3)

  println("Median : ${medianFinder.median()}")

  medianFinder.addNum(8)

  println("Median : ${medianFinder.median()}")

  val medianFinder1 = MedianFinder1()
  with(medianFinder1) {
    addNum(1)
    addNum(6)
    addNum(5)
    addNum(3)
    println("Median : ${median()}")
    addNum(8)
    println("Median : ${median()}")
  }
}

class MedianFinder1 {
  val minHeap = PriorityQueue<Int>()
  val maxHeap = PriorityQueue<Int>(Collections.reverseOrder<Int>())

  var even = true

  fun addNum(num: Int) {
    if (even) {
      minHeap.offer(num)
      maxHeap.offer(minHeap.poll())
    } else {
      maxHeap.offer(num)
      minHeap.offer(maxHeap.poll())
    }

    even = !even
  }

  fun median(): Double {
    val result = if (even) {
      (maxHeap.peek() + minHeap.peek()) / 2.0
    } else {
      maxHeap.peek().toDouble()
    }
    return result
  }
}