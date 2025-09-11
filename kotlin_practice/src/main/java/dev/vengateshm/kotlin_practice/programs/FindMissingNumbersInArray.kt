package dev.vengateshm.kotlin_practice.programs

import kotlin.math.abs

fun main() {
  println(findMissingNumbersInArray(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
  println(findMissingNumbersInArray(intArrayOf(1, 1)))
  println(findMissingNumbersInArray(intArrayOf(2, 3, 3)))
}

fun findMissingNumbersInArray(arr: IntArray): List<Int> {
  val result = mutableListOf<Int>()
  for (i in arr.indices) {
    val index = abs(arr[i]) - 1
    if (arr[index] > 0) arr[index] = -arr[index]
  }
  println(arr.contentToString())
  for (i in arr.indices) {
    if (arr[i] > 0) {
      result.add(i + 1)
    }
  }
  return result
}