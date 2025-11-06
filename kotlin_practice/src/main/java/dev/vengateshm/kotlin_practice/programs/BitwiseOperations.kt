package dev.vengateshm.kotlin_practice.programs

fun main() {

  var a = 5
  var b = 10

  a = a xor b
  b = a xor b
  a = a xor b

  println("a: $a, b: $b")

  pbr(1)
  pbr(7)

  isEven(5)
  isEven(10)

  println(findOddOccurrence(intArrayOf(4, 1, 4, 2, 1)))
  println(findOddOccurrence(intArrayOf(4, 4, 1, 1, 2)))
}

fun findOddOccurrence(arr: IntArray): Int {
  var result = 0
  for (num in arr) {
    result = result xor num
    println(result)
  }

  return result
}

fun pbr(num: Int) {
  println(Integer.toBinaryString(num))
}

fun isEven(num: Int): Boolean {
  pbr(num)
  pbr(1)
  return num and 1 == 0
}