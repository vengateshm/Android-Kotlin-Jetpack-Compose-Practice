package dev.vengateshm.kotlin_practice.intersection_types

data class Product(
    override val url: String,
    override val name: String,
    val price: Double
) : Named, Addressable
