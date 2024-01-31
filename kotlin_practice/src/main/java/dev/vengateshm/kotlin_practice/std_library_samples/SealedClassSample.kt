package dev.vengateshm.kotlin_practice.std_library_samples

sealed class Vehicle {
    data object TwoWheeler : Vehicle()
    data object ThreeWheeler : Vehicle()
    data object FourWheeler : Vehicle()
    data object MultiWheeler : Vehicle()
}

fun main() {
    Vehicle::class.sealedSubclasses.map {
        it.objectInstance as Vehicle
    }.forEach {
        println(it)
    }
}