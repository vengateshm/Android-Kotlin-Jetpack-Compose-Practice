package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(intToRoman(33))
    println(intToRoman(45))
}

fun intToRoman(num: Int): String {
    val map = LinkedHashMap<String, Int>()
    map["M"] = 1000
    map["CM"] = 900
    map["D"] = 500
    map["CD"] = 400
    map["C"] = 100
    map["XC"] = 90
    map["L"] = 50
    map["XL"] = 40
    map["X"] = 10
    map["IX"] = 9
    map["V"] = 5
    map["IV"] = 4
    map["I"] = 1

    val sb = StringBuilder()
    var num = num
    for (entry in map) {
        while (num >= entry.value) {
            num -= entry.value
            sb.append(entry.key)
        }
    }
    return sb.toString()
}