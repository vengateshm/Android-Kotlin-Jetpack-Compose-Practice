package dev.vengateshm.kotlin_practice.intersection_types

data class User1(
    override val url: String,
    override val name: String,
    val age: Int
) : NamedAddressable
