package dev.vengateshm.kotlin_practice

fun main() {
  println(summation())
  println(summation2())
  println(product())
  avgMaxMin()
  multiDimensionalVectors()
}

fun summation(): Double {
  var sum = 0.0
  for (i in 1..10) {
    sum += 1.0 / (i.toDouble() + 1.0)
  }
  return sum
}

fun summation2(): Double {
  return (1..10).map { 1.0 / (it.toDouble() + 1.0) }.sum()
//  return (1..10).sumOf { 1.0 / (it.toDouble() + 1.0) }
}

fun product(): Long {
  return (1..3).fold(1L, Long::times)
}

fun avgMaxMin() {
  val fa = floatArrayOf(1f, 2f, 4f, 10f)
  println(fa.average())
  println(fa.max())
  println(fa.min())
}

fun multiDimensionalVectors() {
  val mx = arrayOf(
    arrayOf(floatArrayOf(2f, 2f, 2f)),
    arrayOf(floatArrayOf(2f, 2f, 2f)),
    arrayOf(floatArrayOf(2f, 2f, 2f)),
  )
  val normArray = mx.map { col ->
    col.map { row ->
      row.map {
        it / 255f
      }
    }
  }

  println(normArray)
}