package dev.vengateshm.kotlin_practice.programs

fun main() {
//    println(findAnagramsPatternsStartIndicesInString("", "abc").toString())
//    println(findAnagramsPatternsStartIndicesInString("abc", "abca").toString())
    println(findAnagramsPatternsStartIndicesInString("abczzbcabcadeb", "abc").toString())
//    println(findAnagramsPatternsStartIndicesInString("abacab", "ab").toString())
}

fun findAnagramsPatternsStartIndicesInString(str: String, pattern: String): List<Int>? {
    // Handle corner cases
    if (isInvalid(str, pattern)) {
        println("Invalid string or pattern")
        return null
    }
    if (pattern.length > str.length) {
        println("Pattern is larger than string")
        return null
    }
    val windowSize = pattern.length
    val strLength = str.length
    val patternCharCountMap = mutableMapOf<Char, Int>()
    val stringCharCountMap = mutableMapOf<Char, Int>()
    val indices = mutableListOf<Int>()

    // Generate count map for first window in str and pattern
    for (i in 0 until windowSize) {
        generateWindowCharCountMap(pattern[i], patternCharCountMap)
        generateWindowCharCountMap(str[i], stringCharCountMap)
    }

    // Start at the end of the first window
    for (windowRightBound in windowSize until strLength) {
        if (compareMap(patternCharCountMap, stringCharCountMap)) {
            indices.add(windowRightBound - windowSize)
        }

        // Remove first character in the window
        val oldCharIndex = windowRightBound - windowSize
        val oldChar = str[oldCharIndex]
        var oldCharCount = stringCharCountMap[oldChar]!!
        if (oldCharCount == 1) {
            stringCharCountMap.remove(oldChar)
        } else {
            oldCharCount--
            stringCharCountMap[oldChar] = oldCharCount
        }

        // Add the new char to the window
        generateWindowCharCountMap(str[windowRightBound], stringCharCountMap)
    }

    // At this point last window comparison remains
    // so compare one last time
    if (compareMap(patternCharCountMap, stringCharCountMap)) {
        indices.add(strLength - windowSize)
    }

    return indices
}

fun generateWindowCharCountMap(char: Char, map: MutableMap<Char, Int>) {
    var count = map[char]
    if (count == null) {
        count = 0;
    }
    count++
    map[char] = count
}

fun isInvalid(str: String?, pattern: String?): Boolean {
    return str.isNullOrEmpty() || pattern.isNullOrEmpty()
}

fun compareMap(patternMap: Map<Char, Int>, strMap: Map<Char, Int>): Boolean {
    for (entry in patternMap) {
        val char = entry.key
        val count = entry.value
        if (strMap[char] == null || strMap[char] != count) {
            return false
        }
    }
    return true
}