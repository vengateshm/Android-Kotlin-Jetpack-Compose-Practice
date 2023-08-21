package dev.vengateshm.kotlin_practice.programs

fun main() {
    // Given 2 array of non decreasing order
    /*val a1 = intArrayOf(2, 3, 5, 6, 8, 90)
    val a2 = intArrayOf(1, 4, 8)*/
    val a1 = intArrayOf(1, 2, 3)
    val a2 = intArrayOf(2, 4)

    println(minimumCommonValue(a1, a2))
}

fun minimumCommonValue(a1: IntArray, a2: IntArray): Int {
    var i = 0
    var j = 0

    while (i < a1.size && j < a2.size) {
        if (a1[i] == a2[j]) {
            println(i)
            println(j)
            return a1[i]
        } else if (a1[i] > a2[j]) {
            j++
        } else {
            i++
        }
    }

    return -1
}