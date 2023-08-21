package dev.vengateshm.kotlin_practice.programs

import java.util.LinkedList

class Deque<T> {
    private val items: LinkedList<T> = LinkedList()

    fun addFirst(item: T) {
        items.addFirst(item)
    }

    fun addLast(item: T) {
        items.addLast(item)
    }

    fun removeFirst(): T? {
        if (isEmpty()) return null
        return items.removeFirst()
    }

    fun removeLast(): T? {
        if (isEmpty()) return null
        return items.removeLast()
    }

    fun peekFirst(): T? {
        if (isEmpty()) return null
        return items.first
    }

    fun peekLast(): T? {
        if (isEmpty()) return null
        return items.last
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun size(): Int {
        return items.size
    }
}

fun main() {
    val deque = Deque<Int>()

    deque.addLast(1)
    deque.addLast(2)
    deque.addFirst(3)
    deque.addLast(4)

    println("Deque size: ${deque.size()}")
    println("Front element: ${deque.peekFirst()}")
    println("Back element: ${deque.peekLast()}")

    while (!deque.isEmpty()) {
        println("Removed: ${deque.removeFirst()}")
    }
}