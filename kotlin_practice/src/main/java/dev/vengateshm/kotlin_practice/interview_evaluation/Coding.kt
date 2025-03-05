package dev.vengateshm.kotlin_practice.interview_evaluation

fun main() {
    println("Is Anagram : ${isAnagram(" eat ", " tea ")}")
    println("Is Palindrome : ${"Racecar".isPalindrome()}")
    println("Is Palindrome : ${"hello".isPalindrome()}")
    println(sumOfEvens(listOf(1, 2, 3, 4, 5)))

    println(rotateRightArray(intArrayOf(1, 2, 3, 4, 5, 6, 7), 3).contentToString())
    println(rotateLeftArray(intArrayOf(1, 2, 3, 4, 5, 6, 7), 3).contentToString())

    println(mostFrequentElement(intArrayOf(1, 3, 3, 2, 1, 3, 4, 4, 4, 4, 5)))

    println(reverseWords(arrayOf("android", "kotlin", "developer")).contentToString())

    println(areStringArraysEqual(arrayOf("ab", "c"), arrayOf("a", "bc")))
    println(areStringArraysEqual1(arrayOf("ab", "c"), arrayOf("a", "bc")))

    println(findPairSum(intArrayOf(1, 2, 3, 4, 5, 6), 7))

    println(groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")))

    println(firstNonRepeatingChar("kotlink"))
}

fun isAnagram(str1: String, str2: String): Boolean {
    if (str1.length != str2.length)
        return false
    val str1 = str1.replace("\\s".toRegex(), "").lowercase()
    val str2 = str2.replace("\\s".toRegex(), "").lowercase()
    return str1.toCharArray().sorted() == str2.toCharArray().sorted()
}

fun String.isPalindrome(): Boolean {
    val input = this.replace("\\s".toRegex(), "").lowercase()
    return input == input.reversed()
}

fun sumOfEvens(nums: List<Int>): Int {
    return nums.filter { it % 2 == 0 }.sum()
}

fun rotateRightArray(nums: IntArray, k: Int): IntArray {
    val shift = k % nums.size
    val part1 = nums.takeLast(shift)
    val part2 = nums.take(nums.size - shift)
    return (part1 + part2).toIntArray()
}

fun rotateLeftArray(nums: IntArray, k: Int): IntArray {
    val shift = k % nums.size
    val part1 = nums.take(shift)
    val part2 = nums.takeLast(nums.size - shift)
//    return (part2 + part1).toIntArray()
    return (nums.drop(shift) + nums.take(shift)).toIntArray()
}

fun mostFrequentElement(nums: IntArray): Int {
    return nums.groupBy { it }.maxByOrNull { it.value.size }?.key ?: -1
}

fun reverseWords(words: Array<String>): Array<String> {
    return words.map { it.reversed() }.toTypedArray()
}

fun areStringArraysEqual(word1: Array<String>, word2: Array<String>): Boolean {
    return word1.joinToString("") == word2.joinToString("")
}

fun areStringArraysEqual1(word1: Array<String>, word2: Array<String>): Boolean {
    val combined1 = StringBuilder()
    for (word in word1) combined1.append(word)

    val combined2 = StringBuilder()
    for (word in word2) combined2.append(word)

    return combined1.toString() == combined2.toString()
}

fun findPairSum(nums: IntArray, target: Int): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()
    val seen = mutableSetOf<Int>()
    for (num in nums) {
        val complement = target - num
        if (seen.contains(complement)) {
            result.add(Pair(num, complement))
        }
        seen.add(num)
    }
    return result
}

fun groupAnagrams(words: Array<String>): List<List<String>> {
    return words.groupBy { it.toCharArray().sorted().joinToString("") }.values.toList()
}

fun firstNonRepeatingChar(str: String): Char? {
    val count = str.groupingBy { it }.eachCount()
    return str.firstOrNull { count[it] == 1 }
}