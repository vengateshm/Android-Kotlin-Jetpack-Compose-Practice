package dev.vengateshm.kotlin_practice.programs

fun main() {
    val array = arrayOf("noon", "level", "android")
//    val result = array.groupBy { isPalindrome(it) }
    val result = array.groupBy { it.isPalindrome1() }
    println(result)
}

fun isPalindrome(str: String): Boolean {
    val reversedStrBuilder = StringBuilder()
    for (i in str.length - 1 downTo 0) {
        reversedStrBuilder.append(str[i])
    }
    return str == reversedStrBuilder.toString()
}

fun String.isPalindrome1() = this == this.reverseInPlace()

fun String.reverseInPlace(): String {
    val charArr = toCharArray()
    var start = 0
    var end = charArr.size - 1
    while (start < end) {
        val temp = charArr[start]
        charArr[start] = charArr[end]
        charArr[end] = temp
        start++
        end--
    }
    return String(charArr)
}