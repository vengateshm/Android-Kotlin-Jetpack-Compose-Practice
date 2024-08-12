package dev.vengateshm.kotlin_practice.concurrency

import java.lang.Thread.sleep
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.concurrent.thread
import kotlin.random.Random

val queue = ConcurrentLinkedQueue<String>()

fun main() {

    thread {
        repeat(5) { sleep(1000); queue.add(readData(1)) }
    }
    thread {
        repeat(5) { queue.add(readData(2)) }
    }

    while (true) {
        val data = queue.poll() ?: continue
        processData(data)
    }
}

fun readData(sourceId: Int) = "Source::$sourceId::${Random.nextInt(1000)}"
fun processData(data: String) = println(data)