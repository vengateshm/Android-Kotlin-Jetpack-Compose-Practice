package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {
    val booksToRead = mutableListOf(
        "Tea with Agatha",
        "Mystery on First Avenue",
        "The Ravine of Sorrows",
        "Among the Aliens",
        "The Kingsford Manor Mystery",
    )

    runBlocking {
        launch { printList(booksToRead) }
        launch { booksToRead.add("Beyond the Expanse") }
        launch {
            //printSomething()
        }
    }
}

private suspend fun printList(list: List<String>) {
    val copy = list.toList()
    println("List contents:")
    list.forEach {
        println(" - $it")
        yield()
    }
}

private suspend fun printSomething() {
    println("Something")
}