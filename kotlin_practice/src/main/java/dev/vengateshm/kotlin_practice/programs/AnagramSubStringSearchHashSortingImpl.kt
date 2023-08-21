package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(findAnagramsPatternsStartIndicesInStringBySortApproach("abacab", "ab").toString())
    println(
        findAnagramsPatternsStartIndicesInStringBySortApproach(
            "abczzbcabcadeb", "abc"
        ).toString()
    )
}

fun findAnagramsPatternsStartIndicesInStringBySortApproach(
    str: String,
    pattern: String,
): List<Int>? {
    // Handle corner cases
    if (isInvalid(str, pattern)) {
        println("Invalid string or pattern")
        return null
    }
    if (pattern.length > str.length) {
        println("Pattern is larger than string")
        return null
    }
    val indices = mutableListOf<Int>()
    val patternCharArr = pattern.toCharArray().also { it.sort() }
    val windowSize = patternCharArr.size
    val strLength = str.length
    for (i in 0..strLength - windowSize) {
        val subStr = str.substring(i, i + windowSize)
        val sortedSubStrArr = subStr.toCharArray().also { it.sort() }
        if (patternCharArr.contentEquals(sortedSubStrArr)) {
            indices.add(i)
        }
    }
    return indices
}