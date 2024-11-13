package dev.vengateshm.compose_material3.api_android.app_search

data class CoffeeScreenUiState(
    val searchQuery: String = "",
    val coffeeList: List<Coffee> = emptyList(),
)
