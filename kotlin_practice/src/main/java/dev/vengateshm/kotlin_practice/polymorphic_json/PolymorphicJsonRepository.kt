package dev.vengateshm.kotlin_practice.polymorphic_json

import java.io.File

class PolymorphicJsonRepository {
    fun getJson(): String {
        return File("kotlin_practice/src/main/resources/polymorphic.json").readText()
    }
}