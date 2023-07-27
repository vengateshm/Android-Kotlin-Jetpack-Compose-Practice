package dev.vengateshm.kotlin_practice.std_library_samples

import uy.klutter.core.collections.asReadOnly
import uy.klutter.core.collections.toImmutable
import java.util.Collections

fun main() {
    val mutableList = mutableListOf<String>()
    mutableList.add("Hello")
    println(mutableList.joinToString())

    val immutableList: List<String> = mutableList.toList()
//    immutableList[0] = "World" //Compile Error

    val backToMutableList = immutableList.toMutableList()
    backToMutableList[0] = "World"
    println(backToMutableList.joinToString())

    val immutableListCasted: List<String> = mutableList
//    immutableListCasted[0] = "World" //Compile error
    val backToMutableListCasted = immutableListCasted as MutableList<String>
    backToMutableListCasted[0] = "World"
    println(backToMutableListCasted.joinToString())
    println(mutableList.joinToString())

    val immutableList1 = ImmutableList(mutableList)
//    immutableList[0]="" //Compile error
//    val backToMutable1 = immutableList1 as MutableList<String> // Cast Exception

    // Kotlinx immutable or Klutter library can be used
    val list = mutableListOf<String>()
    val map = mutableMapOf<String, String>()
    val set = mutableSetOf<String>()
    list.toImmutable()
    map.toImmutable()
    set.asReadOnly()

    val n = Collections.unmodifiableList(listOf(1, 2, 3))
    //n.add(4) Throws unsupported operation exception
}

class ImmutableList<T>(private val protectedList: List<T>) : List<T> by protectedList