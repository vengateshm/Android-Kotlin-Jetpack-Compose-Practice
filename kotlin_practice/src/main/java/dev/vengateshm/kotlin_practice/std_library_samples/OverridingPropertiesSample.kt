package dev.vengateshm.kotlin_practice.std_library_samples

open class ABC(b: Int = 100) {
    open val a = 10

    init {
        println("Init Block : Base Class")
        println("Value : $a") // Prints 0 - After init block only property initialized
        println("Value : $b")
    }
}

open class XYZ : ABC() {
    override val a: Int
        get() = 15

    init {
        println("Init Block : Child Class XYZ")
    }
}

class PQR(override val a: Int) : XYZ() {
    init {
        println("Init Block : Child Class PQR")
        println("Value : $a")
        println("Super class value : ${super.a}")
    }
}

fun main() {
    val abc: ABC = PQR(20)
}