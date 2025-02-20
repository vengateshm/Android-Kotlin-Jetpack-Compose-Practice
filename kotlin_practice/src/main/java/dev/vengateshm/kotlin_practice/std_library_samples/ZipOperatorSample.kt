package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    val l1 = listOf("a", "b", "c")
    val l2 = listOf("a", "e", "c")
    val differentIndex = l1.zip(l2).indexOfFirst { it.first != it.second }
    println(differentIndex)
}