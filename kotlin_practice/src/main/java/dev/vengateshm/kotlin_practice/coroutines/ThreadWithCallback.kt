package dev.vengateshm.kotlin_practice.coroutines

import kotlin.concurrent.thread

fun main() {
//    println("Start")
//    Thread.sleep(3000L)
//    println("End")
    println("Main start")
    getData { data, error ->
        data?.let(::println)
        error?.let(::println)
    }
    println("Main end")
}

fun getData(completion: (String?, Throwable?) -> Unit) {
    thread {
        println("Start ${Thread.currentThread().name}")
        try {
            Thread.sleep(3000L)
            completion.invoke("Completed", null)
        } catch (e: Exception) {
            completion.invoke(null, e)
        }
        println("End ${Thread.currentThread().name}")
    }
}