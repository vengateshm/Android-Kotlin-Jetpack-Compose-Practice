package dev.vengateshm.kotlin_practice.programs

import kotlin.math.abs

fun main() {
  println(findDisappearedNumbers1(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
  println(findDisappearedNumbers1(intArrayOf(1, 1)))
  println(findDisappearedNumbers2(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
  println(findDisappearedNumbers2(intArrayOf(1, 1)))
  println(findDisappearedNumbers3(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
  println(findDisappearedNumbers3(intArrayOf(1, 1)))
}

fun findDisappearedNumbers1(nums: IntArray): List<Int> {
  val result = mutableListOf<Int>()
  for (i in 1..nums.size) {
    var found = false
    for (j in nums.indices) {
      if (i == nums[j]) {
        found = true
      }
    }
    if (!found) result.add(i)
  }
  return result
}

fun findDisappearedNumbers2(nums: IntArray): List<Int> {
  val map = mutableMapOf<Int, Boolean>()
  val result = mutableListOf<Int>()
  for (num in nums) {
    map[num] = true
  }

  for (i in 1..nums.size) {
    if (map.containsKey(i).not()) {
      result.add(i)
    }
  }

  return result
}

fun findDisappearedNumbers3(nums: IntArray): List<Int> {
  val result = mutableListOf<Int>()
  for (i in 0 until nums.size) {
    val index = abs(nums[i]) - 1
    if (nums[index] > 0) {
      nums[index] = -nums[index]
    }
  }

  for (i in 0 until nums.size) {
    if (nums[i] > 0) {
      result.add(i + 1)
    }
  }

  return result
}