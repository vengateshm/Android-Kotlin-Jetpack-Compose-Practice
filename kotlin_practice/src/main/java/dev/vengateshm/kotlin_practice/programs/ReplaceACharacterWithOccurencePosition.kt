package dev.vengateshm.kotlin_practice.programs

fun main() {
    val str = "topentextt"
    val toReplace = 't'

//    method1(str, toReplace)
    method2(str, toReplace)
}

fun method1(str: String, toReplace: Char) {
    if (str.indexOf(toReplace) == -1) {
        println("Character not present in string")
        return
    }
    val charArr = str.toCharArray()
    var count = 1
    for (i in charArr.indices) {
        if (charArr[i] == toReplace) {
            charArr[i] = (count++).toString()[0]
        }
    }
    println(charArr.concatToString())
}

fun method2(str: String, toReplace: Char) {
    if (str.indexOf(toReplace) == -1) {
        println("Character not present in string")
        return
    }

    var count = 1
    var s = str
    for (i in str.indices) {
        if (str[i] == toReplace) {
            s = s.replaceFirst(s[i].toString(), (count++).toString())
        }
    }
    println(s)
}
