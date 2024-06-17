package dev.vengateshm.kotlin_practice.clean_code_tips

import java.util.Arrays

fun main() {
    val numbers = arrayOf(4, 2, 2, 8, 3, 3, 1)
    // This also does the job for you in this case
    Arrays.sort(numbers)
}

fun countSort(numbers: Array<Int>) {
    if (numbers.isEmpty())
        return
    println(numbers.contentToString())
    val max = numbers.max()
    val countArr = Array(size = max + 1) { 0 }
    for (number in numbers) {
        countArr[number]++
    }
    for (i in 1 until countArr.size) {
        countArr[i] += countArr[i - 1]
    }
    val output = IntArray(numbers.size)
    for (i in numbers.size - 1 downTo 0) {
        val num = numbers[i]
        output[countArr[num] - 1] = num
        countArr[num]--
    }
    for (i in output.indices) {
        numbers[i] = output[i]
    }
    println(numbers.contentToString())
}