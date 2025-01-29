package dev.vengateshm.xml_kotlin.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(jsonString: String) = try {
    this.fromJson(jsonString, object : TypeToken<T>() {}.type)
} catch (e: Exception) {
    null
}