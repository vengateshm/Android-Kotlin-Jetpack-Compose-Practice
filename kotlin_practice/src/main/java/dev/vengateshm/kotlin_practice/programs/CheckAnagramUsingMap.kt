package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(checkAnagramUsingMap("listen", "silent"))
    println(checkAnagramUsingMap1("listen", "silent"))
    println(checkAnagramUsingMap1("listen", "silents"))
}

fun checkAnagramUsingMap(str1: String, str2: String): Boolean {
    return str1.groupingBy { it }.eachCount() == str2.groupingBy { it }.eachCount()
}

fun checkAnagramUsingMap1(str1: String, str2: String): Boolean {
    if (str1.length != str2.length) return false
    val str1Map = str1.groupingBy { it }.eachCount().toMutableMap()

    for (char in str2) {
        if (!str1Map.contains(char)) return false
        str1Map[char] = str1Map[char]!! - 1
        if (str1Map[char] == 0) str1Map.remove(char)
    }

    return str1Map.isEmpty()
}