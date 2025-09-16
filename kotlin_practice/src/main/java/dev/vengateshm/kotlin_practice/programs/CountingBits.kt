package dev.vengateshm.kotlin_practice.programs

fun main() {
  println(countBits(0))
  println(countBits(1))
  println(countBits(2))
  println(countBits(5))

  println(countBits1(0).contentToString())
  println(countBits1(1).contentToString())
  println(countBits1(2).contentToString())
  println(countBits1(5).contentToString())
}

// T - O(n log n)
// S - O(n)
fun countBits(n: Int): List<Int> {
  val result = mutableListOf<Int>()
  for (i in 0..n) {
    var sum = 0
    if (i != 0) {
      var num = i
      while (num > 0) {
        sum += num % 2
        num /= 2
      }
    }
    result.add(sum)
  }
  return result
}

// T - O(n)
// S - O(n)
fun countBits1(n: Int): IntArray {
  val mem = IntArray(n + 1)
  if (n == 0) return mem
  mem[0] = 0
  mem[1] = 1
  for (i in 2..n) {
    if (i % 2 == 0) {
      mem[i] = mem[i / 2]
    } else {
      mem[i] = mem[i / 2] + 1
    }
  }
  return mem
}