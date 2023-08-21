package dev.vengateshm.kotlin_practice.design_patterns

data class Product(val id: String, val name: String, val price: Double)

class ProductBuilder {
    private var id: String = ""
    private var name: String = ""
    private var price: Double = 0.0

    fun withId(id: String) = apply { this.id = id }
    fun withName(name: String) = apply { this.name = name }
    fun withPrice(price: Double) = apply { this.price = price }

    fun build() = Product(
        id = this.id,
        name = this.name,
        price = this.price
    )
}

fun main() {
    val builder = ProductBuilder()
        .withId("1")
        .withName("Headphones")
        .withPrice(10000.0)
        .build()
    println(builder)
}