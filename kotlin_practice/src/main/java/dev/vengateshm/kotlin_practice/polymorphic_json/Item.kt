package dev.vengateshm.kotlin_practice.polymorphic_json

sealed interface Item {
    val id: Int
    val type: String
    val name: String

    data class Car(
        override val id: Int,
        override val type: String,
        override val name: String,
        val manufacturer: String,
        val year: Int,
    ) : Item

    data class Person(
        override val id: Int,
        override val type: String,
        override val name: String,
        val age: Int,
        val occupation: String,
    ) : Item

    data class Book(
        override val id: Int,
        override val type: String,
        override val name: String,
        val author: String,
        val pages: Int,
    ) : Item
}