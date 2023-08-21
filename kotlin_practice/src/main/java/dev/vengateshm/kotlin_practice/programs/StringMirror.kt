package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(mirrorString("Hello"))
}

fun mirrorString(s: String): String {
    val length = s.length

    val sb = StringBuilder()
    sb.append(s)
    for (i in 0 until length) {
        sb.append(s[length - 1 - i])
    }
    return sb.toString()
}
