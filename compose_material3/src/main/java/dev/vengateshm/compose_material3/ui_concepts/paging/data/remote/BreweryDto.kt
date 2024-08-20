package dev.vengateshm.compose_material3.ui_concepts.paging.data.remote

data class BreweryDto(
    val id: String,
    val name: String,
    val brewery_type: String,
    val address_1: String?,
    val address_2: String?,
    val address_3: String?,
    val city: String,
    val state_province: String?,
    val postal_code: String,
    val country: String,
    val longitude: String?,
    val latitude: String?,
    val phone: String?,
    val website_url: String?,
    val state: String?,
    val street: String?
)

