package dev.vengateshm.kotlin_practice.concurrency

import java.lang.Thread.sleep
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock

class QueueWithMutex {
    private val queue = ArrayDeque<String>()
    private val lock = ReentrantLock()

    fun enqueue(data: String) = lock.withLock {
        queue.addLast(data)
    }

    fun dequeue(): String? = lock.withLock {
        return queue.removeFirstOrNull()
    }
}

val sequentialQueue = QueueWithMutex()

fun main() {

    thread {
        repeat(5) { sleep(1000); sequentialQueue.enqueue(readData(1)) }
    }
    thread {
        repeat(5) { sleep(500); sequentialQueue.enqueue(readData(2)) }
    }

    while (true) {
        val data = sequentialQueue.dequeue() ?: continue
        processData(data)
    }
}