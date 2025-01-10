package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    // Use $$$ if you want to add one $$ to your string
    // $$$ is used for interpolate the value
    val name = "Kotlin"
    val jsonStr = $$$"""
        {
            "$$name": "$$${name.uppercase()}"
        }
    """.trimIndent()
    println(jsonStr)
}
