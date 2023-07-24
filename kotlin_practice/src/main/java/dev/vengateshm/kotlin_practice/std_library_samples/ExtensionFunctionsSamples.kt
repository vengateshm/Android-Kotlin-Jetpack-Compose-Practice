package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    1.format()
    1f.format()
    1.0.format()

    val intNum: Int = 10
    intNum.format()
    val myList = listOf("Tiramisu", "Pancake", "Donut")
    myList.join()
}

fun Number.format() = println(this)

fun Int.formatInt() = println(this)

fun Collection<String>.join() {
    println(this.joinToString(separator = "--"))
}

fun List<String>.joinList() {
    println(this.joinToString(separator = "--"))
}