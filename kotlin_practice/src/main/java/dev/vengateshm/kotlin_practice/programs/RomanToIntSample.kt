package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(romanToIntSample("XXXIII"))
    println(romanToIntSample("XLV"))
}

fun romanToIntSample(s: String): Int {
    val charMap = HashMap<Char, Int>()
    charMap['I'] = 1
    charMap['V'] = 5
    charMap['X'] = 10
    charMap['L'] = 50
    charMap['C'] = 100
    charMap['D'] = 500
    charMap['M'] = 1000

    var result = 0

    for (i in 0 until s.length - 1) {
        if (charMap[s[i]]!! >= charMap[s[i + 1]]!!) {
            //XI
            result += charMap[s[i]]!!
        } else {
            //IX
            result -= charMap[s[i]]!!
        }
    }
    result += charMap[s[s.length - 1]]!!

    return result
}