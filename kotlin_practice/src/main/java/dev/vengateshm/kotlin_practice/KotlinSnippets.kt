package dev.vengateshm.kotlin_practice

import java.io.File

fun main() {
    println(qix(bar(foo(123))))

    foo(123).let(::bar).let(::qix).also(::println)

    val condition = true
    when (condition) {
        true -> println("true")
        false -> println("false")
    }
}

fun foo(n: Int): Int {
    return n + 1
}

fun bar(n: Int): Int {
    return n + 1
}

fun qix(n: Int): Int {
    return n + 1
}

fun makeDir(path: String) = File(path).also { it.mkdirs() }
fun makeDir1(path: String): File {
    val file = File(path)
    file.mkdirs()
    return file
}