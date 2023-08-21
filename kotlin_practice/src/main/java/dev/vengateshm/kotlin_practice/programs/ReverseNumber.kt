package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(reverseNumber(123))
}

fun reverseNumber(num: Int): Int {
    var cNum = num
    var rev = 0
    while (cNum != 0) {
        val rem = cNum % 10
        println(rem)
        rev = rev * 10 + rem
        println(rev)
        cNum /= 10
    }
    return rev
}
