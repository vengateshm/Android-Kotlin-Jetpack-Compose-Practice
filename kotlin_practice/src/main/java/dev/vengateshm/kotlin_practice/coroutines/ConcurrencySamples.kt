package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {
    orderServiceConcurrent4()
}

fun concurrency1() {
    runBlocking {
        launch {
            println("One")
            yield()
            println("Two")
            yield()
            println("Three")
        }
        launch {
            println("Four")
            yield()
            println("Five")
        }
    }
}

enum class Product(val description: String, val deliveryTime: Long) {
    DOORS("door", 750), WINDOWS("windows", 1_250)
}

fun order(item: Product): Product {
    println("ORDER EN ROUTE >>> The ${item.description} are on the way.")
    Thread.sleep(item.deliveryTime)
    println("ORDER DELIVERED >>> Your ${item.description} has arrived.")
    return item
}

fun perform(taskName: String) {
    println("STARTING TASK >>> $taskName")
    Thread.sleep(1_000)
    println("FINISHED TASK >>> $taskName")
}

fun orderService() {
    val windows = order(Product.WINDOWS)
    val doors = order(Product.DOORS)
    perform("laying bricks")
    perform("installing ${windows.description}")
    perform("installing ${doors.description}")
}

fun orderServiceConcurrent() {
    runBlocking {
        val windows = async { order(Product.WINDOWS) }
        val doors = async { order(Product.DOORS) }
        launch {
            perform("laying bricks")
            perform("installing ${windows.await().description}")
            perform("installing ${doors.await().description}")
        }
    }
}

fun orderServiceConcurrent1() {
    runBlocking {
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) { order(Product.DOORS) }
        launch(Dispatchers.Default) {
            perform("laying bricks")
            perform("installing ${windows.await().description}")
            perform("installing ${doors.await().description}")
        }
    }
}

fun orderServiceConcurrent2() {
    runBlocking {
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) { order(Product.DOORS) }
        launch(Dispatchers.Default) {
            perform("laying bricks")
            launch { perform("installing ${windows.await().description}") }
            launch { perform("installing ${doors.await().description}") }
        }
        cancel()
    }
}

fun orderServiceConcurrent3() {
    runBlocking {
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) { order(Product.DOORS) }.also { cancel() }
        launch(Dispatchers.Default) {
            perform("laying bricks")
            launch { perform("installing ${windows.await().description}") }
            launch { perform("installing ${doors.await().description}") }
        }
        cancel()
    }
}

fun orderServiceConcurrent4() {
    runBlocking {
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) {
            throw Exception("Out Of Money!")
            order(Product.DOORS)
        }.also { cancel() }
        launch(Dispatchers.Default) {
            perform("laying bricks")
            launch { perform("installing ${windows.await().description}") }
            launch { perform("installing ${doors.await().description}") }
        }
        cancel()
    }
}