package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    val f1 = curriedAdd(5)
    val f2 = f1(10)
    val f3 = f2(15)
    println(f3)
}

fun curriedAdd(x: Int): (Int) -> (Int) -> Int {
    return { y: Int ->
        { z: Int ->
            x + y + z
        }
    }
}