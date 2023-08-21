package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(removeDuplicatesSortedArrInPlace(intArrayOf(1, 2, 2, 3, 3, 4, 5, 6)))
}

fun removeDuplicatesSortedArrInPlace(a: IntArray): Int {
    var j = 0
    for (i in 1 until a.size) {
        if (a[j] != a[i]) {
            a[j + 1] = a[i]
            j++
        }
    }
    return j + 1
}