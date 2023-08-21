package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(reversePrefix("abcdefd", 'd'))
    println(reversePrefix("xyxzxe", 'z'))
}

fun reversePrefix(str: String, ch: Char): String {
    var start = 0
    var end = -1
    for (i in str.indices) {
        if (str[i] == ch) {
            end = i
            break
        }
    }
    if (end < 0) return str
    val arr = str.toCharArray()
    while (start < end) {
        val temp = arr[start]
        arr[start] = arr[end]
        arr[end] = temp
        start++
        end--
    }

    return String(arr)
}