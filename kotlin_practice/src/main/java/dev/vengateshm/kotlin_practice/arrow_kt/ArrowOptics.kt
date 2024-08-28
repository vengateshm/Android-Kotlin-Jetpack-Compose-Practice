package dev.vengateshm.kotlin_practice.arrow_kt

import arrow.optics.PEvery
import arrow.optics.copy
import arrow.optics.dsl.every
import arrow.optics.optics

@optics
data class Product(val name: String, val price: Double) {
    companion object
}

@optics
data class OrderItem(val product: Product, val quantity: Int) {
    companion object
}

@optics
data class Inventory(val items: List<Product>) {
    companion object
}

fun main() {
    val orderItem = OrderItem(Product("iPhone", 999.99), 2)
    val inventory = Inventory(listOf(Product("iPhone", 999.99), Product("iPad", 499.99)))

    // Without arrow
    val orderItemCopy = orderItem.copy(
        product = orderItem.product.copy(
            price = orderItem.product.price * 0.9,
        ),
    )
    val inventoryCopy = inventory.copy(
        items = inventory.items.map { product ->
            product.copy(price = product.price * 1.1)
        },
    )

    println(orderItemCopy)
    println(inventoryCopy)

    // Optics
    val orderItemOptics = OrderItem.product.price.modify(orderItem) { it * 0.9 }
    val inventoryOptics = Inventory.items.every(PEvery.list()).price.modify(inventory) { it * 1.1 }
    println(orderItemOptics)
    println(inventoryOptics)

    val multipleFields = orderItem.copy {
        OrderItem.product.price.transform { it * 0.5 }
        OrderItem.quantity.set(3)
    }
    println(multipleFields)

    when (val result = getErrorOrProduct()) {
        is Failed -> println("Failed: ${result.error}")
        is Success -> println(
            /*Success(
                result.product.copy(
                    price = result.product.price * 0.9,
                ),
            ),*/
            ErrorOrProduct.success.product.price.modify(result) { it * 0.9 },
        )
    }
}

fun getErrorOrProduct(): ErrorOrProduct {
    return Success(Product("iPhone", 999.99))
}

@optics
sealed interface ErrorOrProduct {
    companion object
}

@optics
data class Success(val product: Product) : ErrorOrProduct {
    companion object
}

@optics
data class Failed(val error: Throwable) : ErrorOrProduct {
    companion object
}