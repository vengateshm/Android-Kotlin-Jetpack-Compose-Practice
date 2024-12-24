package dev.vengateshm.kotlin_practice.programs

fun main() {
    isConfusingNumber(6).also(::println)
    isConfusingNumber(89).also(::println)
    isConfusingNumber(11).also(::println)
}

fun isConfusingNumber(num: Int): Boolean {
    var n = num
    var newNum = 0
    while (n != 0) {
        var digit = n % 10
        if (!isValid(digit))
            return false
        if (digit == 6)
            digit = 9
        else if (digit == 9)
            digit = 6
        newNum = newNum * 10 + digit
        n /= 10
    }
    return newNum != num
}

fun isValid(num: Int): Boolean {
    return when (num) {
        in 2..5, 7 -> false
        else -> true
    }
}