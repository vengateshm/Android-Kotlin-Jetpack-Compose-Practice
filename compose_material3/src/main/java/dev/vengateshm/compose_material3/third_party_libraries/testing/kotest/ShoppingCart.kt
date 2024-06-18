package dev.vengateshm.compose_material3.third_party_libraries.testing.kotest

import android.os.Build
import androidx.annotation.RequiresApi

typealias Quantity = Int

data class Product(val id: String, val name: String, val price: Double)

@RequiresApi(Build.VERSION_CODES.N)
class ShoppingCart {
    val items = mutableMapOf<Product, Quantity>()


    fun addProduct(product: Product, quantity: Quantity) {
        items[product] = items.getOrDefault(product, 0) + quantity
    }

    fun removeProduct(product: Product, quantity: Quantity) {
        require(items.containsKey(product)) { "Product not added to the cart" }
        val currentQuantity = items[product]!!
        require(quantity <= currentQuantity) { "Quantity to remove exceeds the quantity of the product in the cart" }
        if (quantity == currentQuantity) {
            items.remove(product)
        } else {
            items[product] = currentQuantity - quantity
        }
    }

    fun getTotalPrice(): Double {
        return items.entries.sumOf { it.key.price * it.value }
    }

    fun checkout(): Double {
        val totalPrice = getTotalPrice()
        items.clear()
        return totalPrice
    }
}