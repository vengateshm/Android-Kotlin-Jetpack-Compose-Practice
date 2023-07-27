package dev.vengateshm.kotlin_practice.programs

fun main() {
    // Swap two numbers without third variable
    swapNumbers(15, 28)
    // Swap two strings without third variable
//    swapStrings1("Hello","Candidate")
//    swapStrings2("Hello", "Candidate")
}

fun swapNumbers(v1: INTE, v2: INTE) {
    var i1 = v1
    var i2 = v2
    println("Before swapping :\ni1 : $i1,  i2 : $i2")
    i1 += i2
    i2 = i1 - i2
    i1 -= i2
    println("After swapping :\ni1 : $i1,  i2 : $i2")
}

fun swapStrings1(v1: STR, v2: STR) {
    var s1 = v1
    var s2 = v2
    println("Before swapping :\ns1 : $s1,  s2 : $s2")
    s1 = s2 + s1
    s2 = s1.substring(s2.length)
    s1 = s1.substringBefore(s2[0])
    println(s1)
    println(s2)
    println("After swapping :\ns1 : $s1, s2 : $s2")
}

fun swapStrings2(v1: STRINGG, v2: STRINGG) {
    var s1 = v1
    var s2 = v2
    println("Before swapping :\ns1 : $s1,  s2 : $s2")
    s1 += s2
    s2 = s1.substring(0, (s1.length - s2.length))
    s1 = s1.substring(s2.length)
    println("After swapping :\ns1 : $s1, s2 : $s2")
}

typealias STR = String
typealias STRINGG = String
typealias INTE = Int