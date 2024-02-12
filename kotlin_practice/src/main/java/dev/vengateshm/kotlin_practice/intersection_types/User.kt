package dev.vengateshm.kotlin_practice.intersection_types

data class User(
    override val url: String,
    override val name: String,
    val age:Int
) : Named, Addressable
