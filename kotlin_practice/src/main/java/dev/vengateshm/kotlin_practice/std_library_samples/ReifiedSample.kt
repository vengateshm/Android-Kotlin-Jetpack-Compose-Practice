package dev.vengateshm.kotlin_practice.std_library_samples

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Fruit(val name:String)

fun main() {
    val fruitJson = "{\"name\":\"Apple\"}"
    val fruit = Gson().fromJson<Fruit>(fruitJson)
    println(fruit.name)
}

inline fun <reified T> Gson.fromJson(json:String) : T {
    return fromJson(json, object : TypeToken<T>() {}.type)
}