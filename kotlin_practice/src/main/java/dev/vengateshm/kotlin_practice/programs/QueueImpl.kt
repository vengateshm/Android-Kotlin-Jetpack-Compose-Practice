package dev.vengateshm.kotlin_practice.programs

class Queue<T> {
    private val items: MutableList<T> = mutableListOf()

    fun enqueue(item: T) {
        items.add(item)
    }

    fun dequeue(): T? {
        if (isEmpty()) return null
        return items.removeAt(0)
    }

    fun peek(): T? {
        if (isEmpty()) return null
        return items[0]
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun size(): Int {
        return items.size
    }
}

fun main() {
    val queue = Queue<Int>()

    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)

    println("Queue size: ${queue.size()}")
    println("Front element: ${queue.peek()}")

    while (!queue.isEmpty()) {
        println("Dequeued: ${queue.dequeue()}")
    }
}