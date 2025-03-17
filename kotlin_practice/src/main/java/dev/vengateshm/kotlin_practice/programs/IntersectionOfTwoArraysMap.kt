package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(intersectionOfArraysUsingMap(intArrayOf(1, 2, 2, 3, 4, 5), intArrayOf(1, 2, 2)))
}

fun intersectionOfArraysUsingMap(arr1: IntArray, arr2: IntArray): List<Int> {
    val result = mutableListOf<Int>()

    val arr1Map = arr1.toList().groupingBy { it }.eachCount().toMutableMap()

    for (num in arr2) {
        if (arr1Map.containsKey(num)) {
            result.add(num)
            arr1Map[num] = arr1Map[num]!! - 1
            if (arr1Map[num] == 0) arr1Map.remove(num)
        }
    }

    return result
}