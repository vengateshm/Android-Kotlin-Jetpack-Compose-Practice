package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(findCommonElements(intArrayOf(1, 2, 2, 3, 4, 5), intArrayOf(1, 2, 2)))
}

fun findCommonElements(arr1: IntArray, arr2: IntArray): List<Int> {
    val arr1Map = mutableMapOf<Int, Int>()
    val commonElements = mutableListOf<Int>()

    for (num in arr1) {
        arr1Map[num] = arr1Map.getOrDefault(num, 0) + 1
    }

    for (num in arr2) {
        if (arr1Map.containsKey(num) && arr1Map[num]!! > 0) {
            commonElements.add(num)
            arr1Map[num] = arr1Map[num]!! - 1
        }
    }

    return commonElements
}