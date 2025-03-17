package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")))
}

fun groupAnagrams(words: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()

    for (word in words) {
        val key = word.toCharArray().sorted().joinToString("")
        map.putIfAbsent(key, mutableListOf())
        map[key]!!.add(word)
    }

    return map.values.toList()
}