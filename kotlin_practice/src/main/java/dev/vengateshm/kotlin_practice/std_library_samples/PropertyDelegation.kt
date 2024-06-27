package dev.vengateshm.kotlin_practice.std_library_samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.properties.Delegates

var favorite: String by Delegates.observable("None") { property, old, new ->
    println("${property.name} changing from $old to $new")
}

var vetoable: String by Delegates.vetoable("None") { property, old, new ->
    "Banana" !in new
}

fun main() {
    favorite = "Apple"
    favorite = "Banana"
    favorite = "Citrus"

    vetoable = "Apple Pie"
    vetoable = "Banana Smoothie"
    vetoable = "Banana Smoothie"
//    vetoable = "Choco lava cake"
    runBlocking {
        delay(2000)
        println(vetoable)
    }
}