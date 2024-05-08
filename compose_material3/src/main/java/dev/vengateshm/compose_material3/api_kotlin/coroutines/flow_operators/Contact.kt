package dev.vengateshm.compose_material3.api_kotlin.coroutines.flow_operators

data class Contact(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
)

val contacts = listOf(
    Contact("John", "Doe", "123-456-7890"),
    Contact("Jane", "Smith", "987-654-3210"),
    Contact("Alice", "Johnson", "555-123-4567"),
    Contact("Bob", "Williams", "111-888-7777")
)