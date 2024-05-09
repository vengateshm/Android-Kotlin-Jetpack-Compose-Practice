package dev.vengateshm.kotlin_practice.testing.tdd

fun runLengthEncoding(input: String): String {
    if (input.isEmpty()) return ""
    require(!input.first().isDigit())
//    return "${input.length}$input"
//    return "${input.length}${input.first()}"
    var currentChar = input.first().toString()
    var currentCount = 1
    var result = ""
    for (i in 1 until input.length) {
        if(input[i].isDigit()) throw IllegalArgumentException()
        if(input[i].toString() == currentChar) {
            currentCount +=1
        } else {
            result += "$currentCount$currentChar"
            currentChar = input[i].toString()
            currentCount = 1
        }
    }
    result += "$currentCount$currentChar"
    return result
}