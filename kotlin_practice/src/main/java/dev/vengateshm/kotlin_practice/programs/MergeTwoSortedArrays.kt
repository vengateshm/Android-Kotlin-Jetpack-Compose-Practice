package dev.vengateshm.kotlin_practice.programs

fun main() {
  merge(
    intArrayOf(1, 3, 4, 5, 8, 0, 0, 0, 0, 0, 0),
    intArrayOf(1, 2, 4, 6, 7, 9),
    m = 5,
    n = 6,
  )
  merge1(
    intArrayOf(1, 3, 4, 5, 8, 0, 0, 0, 0, 0, 0),
    intArrayOf(1, 2, 4, 6, 7, 9),
    m = 5,
    n = 6,
  )
}

// T: O(m+n) log (m+n)
// S: O(1)
fun merge(arr1: IntArray, arr2: IntArray, m: Int, n: Int) {
  var j = 0
  for (i in m until (m + n)) {
    arr1[i] = arr2[j++]
  }
  arr1.sort()
  println(arr1.contentToString())
}

// Two pointer
// T : O(m+n)
fun merge1(arr1: IntArray, arr2: IntArray, m: Int, n: Int) {
  var arr1Tail = m - 1
  var arr2Tail = n - 1
  var combinedTail = m + n - 1

  while (arr1Tail >= 0 && arr2Tail >= 0) {
    if (arr1[arr1Tail] > arr2[arr2Tail]) {
      arr1[combinedTail] = arr1[arr1Tail]
      arr1Tail--
    } else {
      arr1[combinedTail] = arr2[arr2Tail]
      arr2Tail--
    }
    combinedTail--
  }

  while (arr2Tail >= 0) {
    arr1[combinedTail--] = arr2[arr2Tail--]
  }

  println(arr1.contentToString())
}