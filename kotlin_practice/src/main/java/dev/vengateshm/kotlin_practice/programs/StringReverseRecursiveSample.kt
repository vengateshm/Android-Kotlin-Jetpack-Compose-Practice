package dev.vengateshm.kotlin_practice.programs

fun main() {
    reverseRecursive("Hello")
}

fun reverseRecursive(str: String): String {
    return if (str.length == 1) {
        println(str)
        str
    } else {
        var rev = str[str.length - 1] + reverseRecursive(str.substring(0, str.length - 1))
        println(rev)
        rev
    }
}