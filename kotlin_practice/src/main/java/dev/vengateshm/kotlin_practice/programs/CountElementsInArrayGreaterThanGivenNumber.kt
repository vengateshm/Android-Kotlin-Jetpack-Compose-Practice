package dev.vengateshm.kotlin_practice.programs

import java.util.TreeSet

fun countGreaterElements(arr: IntArray, target: Int): Int {
    val treeSet = TreeSet(arr.toList())

    return treeSet.tailSet(target, false).size
}

fun countLowerElements(arr: IntArray, target: Int): Int {
    val treeSet = TreeSet(arr.toList())

    return treeSet.headSet(target, false).size
}

fun main() {
    val arr = intArrayOf(10, 20, 15, 30, 5, 25)
    val target = 15
    println(countGreaterElements(arr, target))
    println(countLowerElements(arr, target))
}
