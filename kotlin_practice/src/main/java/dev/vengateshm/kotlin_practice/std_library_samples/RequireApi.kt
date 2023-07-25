package dev.vengateshm.kotlin_practice.std_library_samples

import kotlin.random.Random

fun main() {
    val intList = listOf(1,4,6,7,8)
    println(intList.takeRandom())
    println(emptyList<Int>().takeRandom())
}

fun <T> List<T>.takeRandom(): T {
    require(this.isNotEmpty())
    return this[Random.nextInt(this.size)]
}