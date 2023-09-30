package dev.vengateshm.kotlin_practice.junit5

class JUnit5Sample {
    fun isPalindrome(value: String): Boolean {
        val normalizedInput = value.replace(Regex("[^a-zA-Z0-9]"), "")
        return normalizedInput == normalizedInput.reversed()
    }

    fun areaOfRectangle(width: Double, height: Double): Double {
        return width * height
    }
}