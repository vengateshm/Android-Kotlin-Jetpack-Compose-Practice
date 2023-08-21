package dev.vengateshm.kotlin_practice.programs

// n = 5  => 1 2 3 4 5
// time = 2

fun main() {
    //n : 1 -> 2 -> 3
    //t : 2
    //output : t + 1 = 3
    println(pillowHolderIndex(3, 2))
    println(pillowHolderIndex(4, 5))
}

fun pillowHolderIndex(n: Int, time: Int): Int {
    return if (time < n) {
        time + 1
    } else {
        val cycles = time / (n - 1) // If 5 members 4 passes
        val passesLeft = time % (n - 1)
        if (cycles % 2 == 0) {
            passesLeft + 1
        } else {
            n - passesLeft
        }
    }
}