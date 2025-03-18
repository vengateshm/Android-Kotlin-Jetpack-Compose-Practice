package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(addTwoNumberStrings("123", "11"))
    println(addTwoNumberStrings("456", "77"))
}

fun addTwoNumberStrings(s1: String, s2: String): String {
    val sb = StringBuilder()

    var i = s1.lastIndex
    var j = s2.lastIndex
    var carry = 0

    while (i >= 0 || j >= 0 || carry != 0) {
        val d1 = if (i >= 0) s1[i] - '0' else 0
        val d2 = if (j >= 0) s2[j] - '0' else 0
        val sum = d1 + d2 + carry
        carry = sum / 10
        sb.append(sum % 10)
        i--
        j--
    }


    return sb.reversed().toString()
}