package dev.vengateshm.compose_material3.api_kotlin.coroutines.flow_operators

data class ContactState(
    val isLoading: Boolean = false,
    val contacts: List<Contact> = emptyList(),
    val sortType: SortType = SortType.FIRST_NAME
)
