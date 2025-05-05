package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(findUniqueBinaryString(arrayOf("00", "01")))
    println(findUniqueBinaryString(arrayOf("01", "10")))
    println(findUniqueBinaryString(arrayOf("111", "011", "001")))
    println(findUniqueBinaryString(arrayOf("111", "101", "100")))
}

fun findUniqueBinaryString(str: Array<String>): String {
    val n = str.size
    val sb = StringBuilder()
    for (i in 0 until n) {
        if (str[i][i] == '0') sb.append('1') else sb.append('0')
    }
    return sb.toString()
}