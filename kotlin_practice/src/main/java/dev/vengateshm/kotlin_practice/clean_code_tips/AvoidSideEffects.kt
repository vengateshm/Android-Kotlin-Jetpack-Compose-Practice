package dev.vengateshm.kotlin_practice.clean_code_tips

// Better way
fun main() {
    var area = 0.0

    // The function does one job of calculating the area
    // Doesn't modify state outside of its scope
    fun calculateArea(radius:Double) : Double {
        return Math.PI * radius * radius
    }

    area = calculateArea(5.0)
}

