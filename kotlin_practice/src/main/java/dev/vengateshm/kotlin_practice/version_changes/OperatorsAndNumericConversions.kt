package dev.vengateshm.kotlin_practice.version_changes

fun main() {
    val longList = mutableListOf(1L, 2L)
    foo(longList)
    println(longList)
}

fun foo(mList: MutableList<Long>) {
    // Error in Kotlin 1.x
    // OK in 2.0
    mList[0] += 1

    // Desugared into below in 2.0
    //mList.get(0).plus(1)
}