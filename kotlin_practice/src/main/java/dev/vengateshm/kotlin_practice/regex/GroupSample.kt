package dev.vengateshm.kotlin_practice.regex

fun main() {
    val regex = "^([a-zA-Z]{4})(.*)([a-zA-Z]{9})$".toRegex()
    val pattern = regex.toPattern()
    val matcher = pattern.matcher("LikeAndSubscribe")
    if (matcher.matches()) {
        println(matcher.group(0))
        println(matcher.group(1))
        println(matcher.group(2))
        println(matcher.group(3))
    } else {
        //println(matcher.group())
    }
}