package dev.vengateshm.kotlin_practice.polymorphic_json

import java.io.File

class SimpleJsonRepository {
    fun getJson(): String {
        return File("kotlin_practice/src/main/resources/simple.json").readText()
    }

    fun getJsonResponse(): String {
        return File("kotlin_practice/src/main/resources/simple_response.json").readText()
    }
}