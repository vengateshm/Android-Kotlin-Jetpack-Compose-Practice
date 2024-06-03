package dev.vengateshm.kotlin_practice.serialization

import kotlinx.serialization.json.Json

fun main() {
    val str = "Kotlin"
    val jsonString = "[\"$str\"]"
    val result = Json.decodeFromString<List<String>>(jsonString)
    println(result)
}