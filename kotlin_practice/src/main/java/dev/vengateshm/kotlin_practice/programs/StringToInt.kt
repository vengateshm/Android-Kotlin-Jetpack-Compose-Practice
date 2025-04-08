package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(atoi("1337c0d3"))
    println(atoi("words and 987"))
}

fun atoi(string: String): Int {
    val s = string.trim()
    if (s.isEmpty()) return 0
    var i = 0
    var sign = 1
    var num = 0
    if (s[i] == '-' || s[i] == '+') {
        sign = if (s[i] == '-') -1 else 1
        i++
    }
    while (i < s.length && s[i].isDigit()) {
        num = num * 10 + (s[i] - '0')
        if (num * sign > Int.MAX_VALUE)
            return Int.MAX_VALUE
        if (num * sign < Int.MIN_VALUE)
            return Int.MIN_VALUE
        i++
    }

    return sign * num
}