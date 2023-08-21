package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(
        removeDuplicatesFromSortedArray(
            arrayOf(
                1,
                2,
                2,
                3,
                3,
                4,
                4,
                5,
                5,
                6,
                6,
                7
            )
        ).contentToString()
    )
}

fun removeDuplicatesFromSortedArray(arr: Array<Int>): Array<Int> {
    val outputArr = Array(arr.size) { 0 }

    var j = 0
    for (i in 0 until arr.size - 1) {
        if (arr[i] != arr[i + 1]) {
            outputArr[j++] = arr[i]
        }
    }
    outputArr[j++] = arr[arr.size - 1]
    return outputArr
}