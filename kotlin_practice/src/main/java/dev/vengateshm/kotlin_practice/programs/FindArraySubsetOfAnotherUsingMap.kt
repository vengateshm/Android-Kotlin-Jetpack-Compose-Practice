package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(isSubSet(intArrayOf(1, 2, 3, 4, 5), intArrayOf(1, 2)))
    println(isSubSet(intArrayOf(1, 2, 3, 4, 5), intArrayOf(1, 2, 8)))
    println(isSubSet(intArrayOf(1, 2), intArrayOf(1, 2)))
    println(isSubSet(intArrayOf(1, 2), intArrayOf(1, 2, 3)))
}

fun isSubSet(arr1: IntArray, arr2: IntArray): Boolean {
    val arrayToBeMapped = when {
        arr1.size > arr2.size -> arr1
        else -> arr2
    }
    val arrToBeChecked = when {
        arr1.size > arr2.size -> arr2
        else -> arr1
    }
    val arrMap = mutableMapOf<Int, Int>()
    for (num in arrayToBeMapped) {
        arrMap[num] = arrMap.getOrDefault(num, 0) + 1
    }
    for (num in arrToBeChecked) {
        if (!arrMap.containsKey(num) || arrMap[num] == 0) {
            return false
        }
    }
    return true
}