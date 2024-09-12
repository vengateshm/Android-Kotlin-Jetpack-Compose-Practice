package dev.vengateshm.kotlin_practice.programs

fun main() {
//    println(productExceptItself(intArrayOf(1, 2, 1, -2, -1, -2, 1)).contentToString())
//    println(productExceptItself1(intArrayOf(1, 2, 1, -2, -1, -2, 1)).contentToString())
//    println(productExceptItself2(intArrayOf(1, -2, 3, 4, -5)).contentToString())
    println(productExceptItself3(intArrayOf(1, -2, 3, 4, -5)).contentToString())
}

fun productExceptItself(arr: IntArray): IntArray {
    var product = 1
    for (i in arr.indices) {
        product *= arr[i]
    }

    for (i in arr.indices) {
        // Fails if number in arr is zero and throws exception
        arr[i] = product / arr[i]
    }

    return arr
}

// O(n^2)
fun productExceptItself1(arr: IntArray): IntArray {
    val output = IntArray(arr.size)
    for (i in arr.indices) {
        output[i] = arr.indices
            .filter { k -> k != i }
            .map { arr[it] }
            .reduce { a, b -> a * b }
    }
    return output
}

//1, -2, 3, 4, -5
fun productExceptItself2(arr: IntArray): IntArray {
    val output = IntArray(arr.size)
    var left = 1
    var right = 1
    for (i in arr.indices) {
        output[i] = left
        println(output.contentToString())
        left *= arr[i]
    }
    for (i in arr.indices.reversed()) {
        output[i] *= right
        println(output.contentToString())
        right *= arr[i]
    }
    return output
}

fun productExceptItself3(arr: IntArray): IntArray {
    val output = IntArray(arr.size) { 1 }
    var left = 1
    var right = 1
    for (i in arr.indices) {
        output[i] *= left
        left *= arr[i]
        output[arr.size - i - 1] *= right
        right *= arr[arr.size - i - 1]
    }
    return output
}