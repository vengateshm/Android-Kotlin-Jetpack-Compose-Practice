package dev.vengateshm.compose_material3.shared_element_transition

data class ImageData(
    val id: Int,
    val photo: String,
    val author: String,
    val location: String
)

val images = listOf(
    ImageData(
        id = 1,
        photo = "https://images.unsplash.com/photo-1526779259212-939e64788e3c?q=80&w=2674&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        author = "Keegan Houser",
        location = "Zephyr Cove, United States",
    ),
    ImageData(
        id = 2,
        photo = "https://images.unsplash.com/photo-1589287707312-213624549c88?q=80&w=2748&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        author = "BP Miller",
        location = "France",
    )
)