package dev.vengateshm.kotlin_practice.programs

import java.util.TreeSet
import java.util.stream.Collectors

fun main() {
    println(findTopKElementsTreeSet(arrayOf(4, 8, 0, 2, 1), 2))
}

fun findTopKElementsTreeSet(arr: Array<Int>, k: Int): List<Int> {
    val treeSet = TreeSet<Int>(Comparator.reverseOrder())
    treeSet.addAll(arr.toList())
    return treeSet.stream().limit(k.toLong()).collect(Collectors.toList())
}