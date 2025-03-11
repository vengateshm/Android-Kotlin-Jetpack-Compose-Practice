package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(addBinary("11", "1"))
    println(addBinary("1010", "1011"))
}

fun addBinary(s1: String, s2: String): String {
    val sb = StringBuilder()
    var i = s1.length - 1
    var j = s2.length - 1
    var carry = 0
    while (i >= 0 || j >= 0 || carry == 1) {
        var sum = carry
        if (i >= 0) {
            sum += s1[i--] - '0'
        }
        if (j >= 0) {
            sum += s2[j--] - '0'
        }
        sb.append(sum % 2)
        carry = sum / 2
    }
    return sb.reverse().toString()
}