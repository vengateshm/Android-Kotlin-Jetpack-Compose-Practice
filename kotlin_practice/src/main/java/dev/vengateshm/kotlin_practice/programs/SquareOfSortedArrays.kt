package dev.vengateshm.kotlin_practice.programs

import kotlin.math.abs

fun main() {
    println(squareOfSortedArrays(intArrayOf(-4, -1, 0, 1, 3, 10)).contentToString())
}

fun squareOfSortedArrays(a: IntArray): IntArray {
    var s = 0
    var e = a.size - 1
    val outputArr = IntArray(a.size) { 0 }
    var idx = a.size - 1
    while (s <= e) {
        if (abs(a[s]) > abs(a[e])) {
            outputArr[idx] = a[s] * a[s]
            s++
        } else {
            outputArr[idx] = a[e] * a[e]
            e--
        }
        idx--
    }
    return outputArr
}