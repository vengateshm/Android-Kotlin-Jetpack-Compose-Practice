package dev.vengateshm.kotlin_practice.clean_code_tips

//fun main() {
//    // More readable approach
//    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//    val oddSquares = numbers.filter { it % 2 != 0 }.map { it * it }
//    // Output : [1, 9, 25, 49, 81]
//    println(oddSquares)
//}

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // Hard to read despite returning same result
    // and using bitwise & to get odd number which might be performant
    val result = numbers.fold(mutableListOf<Int>()) { acc, n ->
        if (n and 1 == 1) {
            acc.apply { add(n * n) }
        } else {
            acc
        }
    }
    // Output : [1, 9, 25, 49, 81]

    println(result)
}

