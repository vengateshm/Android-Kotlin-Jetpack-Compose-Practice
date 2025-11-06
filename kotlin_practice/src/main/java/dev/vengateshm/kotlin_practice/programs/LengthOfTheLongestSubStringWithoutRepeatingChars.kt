package dev.vengateshm.kotlin_practice.programs

import java.lang.Integer.max

fun main() {
  println(lengthOfTheLongestSubStringWithoutRepeatingChars(""))
  println(lengthOfTheLongestSubStringWithoutRepeatingChars("p"))
  println(lengthOfTheLongestSubStringWithoutRepeatingChars("ppp"))
  println(lengthOfTheLongestSubStringWithoutRepeatingChars("pwwkew"))
  println(lengthOfTheLongestSubStringWithoutRepeatingChars("wpwkew"))
  //println(lengthOfTheLongestSubStringWithoutRepeatingChars1("pwwkew"))
  println(lengthOfTheLongestSubStringWithoutRepeatingChars1("wpwkew"))
  println(lengthOfTheLongestSubStringWithoutRepeatingChars2("wpwkew"))
}

fun lengthOfTheLongestSubStringWithoutRepeatingChars(str: String): Int {
  if (str.isEmpty()) return 0
  if (str.length == 1) return 1
  //pwwkew
  var max = Int.MIN_VALUE
  for (i in str.indices) {
    val visitedEntry = mutableSetOf<Char>()
    for (j in i until str.length) {
      if (visitedEntry.contains(str[j])) {
        max = max(max, j - i)
        break
      } else {
        visitedEntry.add(str[j])
      }
    }
  }
  return max
}

fun lengthOfTheLongestSubStringWithoutRepeatingChars1(str: String): Int {
  if (str.isEmpty()) return 0
  if (str.length == 1) return 1
  val map = LinkedHashMap<Char, Int>()
  //pwwkew
  //wpwkew
  var max = Int.MIN_VALUE
  var lStr = ""
  var i = 0
  while (i < str.length) {
    if (map.containsKey(str[i])) {
      i = map[str[i]]!!
      map.clear()
    } else {
      map[str[i]] = i
    }
    i++
    if (map.size > max) {
      max = map.size
      lStr = map.keys.toString()
    }
  }
  println(max)
  println(lStr)
  return max
}

fun lengthOfTheLongestSubStringWithoutRepeatingChars2(str: String): Int {
  var maxLength = 0
  var left = 0

  val charSet = mutableSetOf<Char>()

  for (right in str.indices) {
    val curChar = str[right]

    while (charSet.contains(curChar)) {
      charSet.remove(str[left])
      left++
    }

    charSet.add(curChar)
    maxLength = max(maxLength, right - left + 1)
    println("left $left right $right charset $charSet maxLength $maxLength")
  }

  return maxLength
}