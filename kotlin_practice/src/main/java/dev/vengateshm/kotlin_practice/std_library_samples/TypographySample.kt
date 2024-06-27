package dev.vengateshm.kotlin_practice.std_library_samples

import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

fun main() {
    val clz = Typography::class
    val properties = clz.memberProperties

    for (property in properties) {
        property.isAccessible = true
        val value = property.getter.call()
        println("${property.name} = $value")
    }
}