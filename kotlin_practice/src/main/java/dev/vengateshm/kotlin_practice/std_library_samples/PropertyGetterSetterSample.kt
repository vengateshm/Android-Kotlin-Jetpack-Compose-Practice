package dev.vengateshm.kotlin_practice.std_library_samples

import java.util.Locale

class America {
    var city: String = "NewYork"
}

class India {
    var city: String = "Delhi"
        get() = field // Getter
        set(value) {  // Setter
            field = value
        }
}

class China {
    var city: String = "Wuhan"
        private set

    fun setCity(city: String) {
        this.city = city
    }
}

class Japan {
    var city: String = "Tokyo"
        get() = field.uppercase(Locale.getDefault())
        set(value) {
            field = "$field $value"
        }
}

fun main() {
    val america = America()
    america.city = "Alaska"
    println("America : ${america.city}")

    val india = India()
    india.city = "Mumbai"
    println("India : ${india.city}")

    val china = China()
    // Try to access private setter, leads to compile time error
    // china.city="Beijing"

    china.setCity("Beijing")
    println("China : ${china.city}")

    val japan = Japan()
    japan.city = "Quoto"
    println("Japan : ${japan.city}")
    println("Japan : ${japan.city}")
}
