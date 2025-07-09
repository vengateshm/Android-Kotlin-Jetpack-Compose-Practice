package dev.vengateshm.kotlin_practice.programs

fun main() {
  println(findMax69Number(9669))
  println(findMax69Number(9996))
  println(findMax69Number(9999))
}

fun findMax69Number(num: Int): String {
  val numStr = num.toString().toMutableList()
  for (i in numStr.indices) {
    if (numStr[i] == '6') {
      numStr[i] = '9'
      break
    }
  }
  return numStr.joinToString("")
}