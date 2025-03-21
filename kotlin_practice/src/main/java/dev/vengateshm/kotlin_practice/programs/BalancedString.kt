package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(isBalanced("3234"))
    println(isBalanced("1234"))
    println(isBalanced1("3234"))
    println(isBalanced1("1234"))
    println(isBalanced2("3234"))
    println(isBalanced2("1234"))
    println(isBalanced3("3234"))
    println(isBalanced3("1234"))
    println(isBalanced4("3234"))
    println(isBalanced4("1234"))
    println(isBalanced5("3234"))
    println(isBalanced5("1234"))
    println(isBalanced6("3234"))
    println(isBalanced6("1234"))
}

fun Int.isEven() = this % 2 == 0
val Int.isOdd
    get() = this.isEven().not()

fun isBalanced(str: String): Boolean {
    return str.filterIndexed { index, _ -> index.isEven() }.map { it - '0' }
        .sum() == str.filterIndexed { index, _ -> index.isOdd }.map { it - '0' }.sum()
}

fun isBalanced1(str: String): Boolean {
    var oddSum = 0
    var evenSum = 0
    for (i in str.indices) {
        when (i.isEven()) {
            true -> evenSum += str[i] - '0'
            false -> oddSum += str[i] - '0'
        }
    }
    return oddSum == evenSum
}

fun isBalanced2(str: String): Boolean {
    var oddSum = 0
    var evenSum = 0
    str.forEachIndexed { index, char ->
        when (index.isEven()) {
            true -> evenSum += str[index] - '0'
            false -> oddSum += str[index] - '0'
        }
    }
    return oddSum == evenSum
}

fun isBalanced3(str: String): Boolean {
    return str.filterIndexed { index, _ -> index.isEven() }
        .sumOf { it - '0' } == str.filterIndexed { index, _ -> index.isOdd }.sumOf { it - '0' }
}

fun isBalanced4(str: String): Boolean {
    val digits = str.map { it - '0' }
    return digits.filterIndexed { index, _ -> index.isEven() }
        .sum() == digits.filterIndexed { index, _ -> index.isOdd }.sum()
}

fun isBalanced5(str: String): Boolean {
    val partitions = str.map { it - '0' }.withIndex().partition { it.index.isEven() }
    return partitions.first.sumOf { it.value } == partitions.second.sumOf { it.value }
}

fun isBalanced6(str: String): Boolean {
    var sign = 1
    var i = 0
    var s = 0
    while (i < str.length) {
        s += sign * (str[i] - '0')
        sign *= -1
        i++
    }
    return s == 0
}