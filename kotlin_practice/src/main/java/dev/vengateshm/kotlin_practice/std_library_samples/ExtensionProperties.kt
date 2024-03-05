package dev.vengateshm.kotlin_practice.std_library_samples

// Extension property to convert kilometers to miles
val Double.kilometersToMiles: Double
    get() = this * 0.621371

// Extension property to convert miles to kilometers
val Double.milesToKilometers: Double
    get() = this * 1.60934

fun main() {
    println(1.0.kilometersToMiles)
    println(3.0.milesToKilometers)
}