package dev.vengateshm.kotlin_practice.programs

fun main() {
  println(intersection(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2)))
  println(intersection(intArrayOf(4, 9, 5), intArrayOf(9, 4, 9, 8, 4)))
}

fun intersection(nums1: IntArray, nums2: IntArray): List<Int> {
  val result = mutableListOf<Int>()
  val map = mutableMapOf<Int, Int>()

  for (num in nums1) {
    map[num] = map.getOrDefault(num, 0) + 1
  }

  for (num in nums2) {
    if (map.containsKey(num) && map[num]!! > 0) {
      result.add(num)
      map[num] = map[num]!! - 1
    }
  }

  return result
}