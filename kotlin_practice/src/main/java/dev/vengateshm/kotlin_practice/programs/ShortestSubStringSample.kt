package dev.vengateshm.kotlin_practice.programs

/**
 *
 * Sample Input For Custom Testing
 * bcaacbc
 * Sample Output
 * 3
 * */

fun main() {
    val input = "bcaacbcz"
    println(shortestSubString(input))
}

private fun shortestSubString(str: String): Int {
    val charCount = charCount(str)
    var smallestLength = str.length

    for (i in 0..str.length) {
        for (j in 0..str.length) {
            val subString = if (i < j) {
                str.substring(i, j)
            } else {
                str.substring(j, i)
            }

            if (charCount(subString) == charCount && subString.length < smallestLength) {
                smallestLength = subString.length
            }
        }
    }

    return smallestLength
}

private fun charCount(str: String): Int {
    val limit = 26
    val countArr = IntArray(limit) { 0 }

    for (i in str.indices) {
        countArr[str[i].code - 97]++
    }

    //println(countArr.contentToString())

    return countArr.filter { it != 0 }.size
}