package dev.vengateshm.kotlin_practice.programs

class Stack<T> {
    private val items: MutableList<T> = mutableListOf()

    fun push(item: T) {
        items.add(item)
    }

    fun pop(): T? {
        if (isEmpty()) return null
        return items.removeAt(items.size - 1)
    }

    fun peek(): T? {
        if (isEmpty()) return null
        return items[items.size - 1]
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun size(): Int {
        return items.size
    }
}

fun main() {
    val stack = Stack<Int>()

    stack.push(1)
    stack.push(2)
    stack.push(3)

    println("Stack size: ${stack.size()}")
    println("Top element: ${stack.peek()}")

    while (!stack.isEmpty()) {
        println("Popped: ${stack.pop()}")
    }
}