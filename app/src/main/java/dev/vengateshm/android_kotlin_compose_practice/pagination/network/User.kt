package dev.vengateshm.android_kotlin_compose_practice.pagination.network

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "firstName")
    val firstName: String,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "lastName")
    val lastName: String,
    @field:Json(name = "picture")
    val picture: String,
    @field:Json(name = "title")
    val title: String,
) {
    val name: String
        get() = "$title $firstName $lastName"
}
