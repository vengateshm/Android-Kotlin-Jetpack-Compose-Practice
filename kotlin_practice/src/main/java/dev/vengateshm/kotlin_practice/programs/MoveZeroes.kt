package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(moveZeroesRight(intArrayOf(0, 1, 0, 3, 12)).contentToString())
    println(moveZeroesLeft(intArrayOf(0, 1, 0, 3, 12)).contentToString())
}

fun moveZeroesRight(a1: IntArray): IntArray {
    var j = 0
    for (i in a1.indices) {
        if (a1[i] != 0) {
            a1[j] = a1[i]
            j++
        }
    }
    for (i in j until a1.size) {
        a1[i] = 0
    }

    return a1
}

fun moveZeroesLeft(a1: IntArray): IntArray {
    var j = a1.size - 1
    for (i in a1.size - 1 downTo 0) {
        if (a1[i] != 0) {
            a1[j] = a1[i]
            j--
        }
    }
    for (i in j downTo 0) {
        a1[i] = 0
    }

    return a1
}