package dev.vengateshm.kotlin_practice.std_library_samples

@JvmInline
value class Km(val value: Double) {
    override fun toString() = "$value km"
}

@JvmInline
value class Miles(val value: Double) {
    override fun toString() = "$value miles"
}

val Km.toMiles: Miles
    get() = Miles(value * 0.621371)

val Miles.toKm: Km
    get() = Km(value * 1.60934)

fun print(km: Km, miles: Miles) {
    println("$km")
    println("$miles")
}

fun main() {
    print(Km(1.0), Miles(3.0))
}