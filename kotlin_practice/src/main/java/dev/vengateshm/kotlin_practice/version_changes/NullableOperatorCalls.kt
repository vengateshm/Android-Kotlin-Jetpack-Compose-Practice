package dev.vengateshm.kotlin_practice.version_changes

fun main() {
    val longList = mutableListOf(1L, 2L)
    foo(Box(longList))
    println(longList)
}

class Box(val mList: MutableList<Long>)

fun foo(box: Box?) {
    box?.mList[0] += 1
    box?.mList[0] += 1L

    // Desugared into
    box?.run { mList.set(0, mList.get(0).plus(2)) }
}