package dev.vengateshm.kotlin_practice.std_library_samples

class Add constructor(a: Int) {
    init {
        println("a $a")
        println("Init Block 1")
    }

    constructor(a: Int, b: Int) : this(a) {
        println("Secondary Constructor : A=$a and B=$b")
    }

    init {
        println("Init Block 2")
    }
}

fun main() {
    Add(1, 2)
}