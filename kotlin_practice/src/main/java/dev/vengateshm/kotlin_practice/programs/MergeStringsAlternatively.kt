package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(mergeStringsAlternatively("abclmnopq", "defghi"))
    println(mergeStringsAlternatively1("abclmnopq", "defghi"))
}

fun mergeStringsAlternatively(s1: String, s2: String): String {
    val result = StringBuilder()
    var i = 0
    var j = 0
    while (i < s1.length && j < s2.length) {
        result.append(s1[i])
        result.append(s2[j])
        i++
        j++
    }
    if (i < s1.length) result.append(s1.substring(i))
    if (j < s2.length) result.append(s2.substring(j))
    return result.toString()
}

// Time o(n), Space o(n) as we use string builder
fun mergeStringsAlternatively1(s1: String, s2: String): String {
    val result = StringBuilder()
    var i = 0
    while (i < s1.length || i < s2.length) {
        if (i < s1.length) result.append(s1[i])
        if (i < s2.length) result.append(s2[i])
        i++
    }
    return result.toString()
}