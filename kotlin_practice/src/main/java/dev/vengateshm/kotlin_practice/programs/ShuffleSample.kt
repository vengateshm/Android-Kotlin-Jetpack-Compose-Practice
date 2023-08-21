package dev.vengateshm.kotlin_practice.programs

import kotlin.random.Random

fun main() {
    println(shuffle(listOf("Adam", "Clark", "John", "Tim") as MutableList<String>))
    println(shuffle(listOf("Adam", "Clark", "John", "Tim") as MutableList<String>))
    println(shuffle(listOf("Adam", "Clark", "John", "Tim") as MutableList<String>))
    println(shuffle(listOf("Adam", "Clark", "John", "Tim") as MutableList<String>))
}

fun <T : Comparable<T>> shuffle(list: MutableList<T>): List<T> {
    for (i in 0 until list.size) {
        val newIndex = Random.nextInt(list.size)
        val temp = list[i]
        list[i] = list[newIndex]
        list[newIndex] = temp
    }
    return list
}