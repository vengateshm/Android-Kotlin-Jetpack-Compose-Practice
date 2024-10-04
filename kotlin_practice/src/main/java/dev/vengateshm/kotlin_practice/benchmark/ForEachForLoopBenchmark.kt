package dev.vengateshm.kotlin_practice.benchmark

import kotlin.time.measureTime

fun main() {
    val list = mutableListOf<Int>()

    repeat(100_000) {
        list.add(it)
    }

    val forEachTime = measureTime {
        list.forEach { _ -> }
    }

    val forEachAlternativeTime = measureTime {
        list.forEach { _ -> }
    }

    val forLoopTime = measureTime {
        for (i in 0 until list.size) {
            list[i]
        }
    }

    println("For each time $forEachTime")
    println("For each alternative time $forEachAlternativeTime")
    println("For loop time $forLoopTime")
}