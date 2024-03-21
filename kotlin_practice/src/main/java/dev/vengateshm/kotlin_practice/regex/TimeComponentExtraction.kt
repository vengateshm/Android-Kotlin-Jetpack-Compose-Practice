package dev.vengateshm.kotlin_practice.regex

fun extractTimeValues(input: String): Pair<Int, Int> {
    var hours = 0
    var minutes = 0

    val parts = input.split(" ")
    var previousPartWasNumber = false
    for (i in parts.indices) {
        val part = parts[i].trim()
        if (part.isEmpty()) continue
        if (part.contains("hr") || part.contains("h")) {
            hours = if (previousPartWasNumber) {
                parts[i - 1].toInt()
            } else {
                part.filter { it.isDigit() }.toInt()
            }
            previousPartWasNumber = false
        } else if (part.contains("min") || part.contains("m")) {
            minutes = if (previousPartWasNumber) {
                parts[i - 1].toInt()
            } else {
                part.filter { it.isDigit() }.toInt()
            }
            previousPartWasNumber = false
        } else {
            previousPartWasNumber = part.all { it.isDigit() }
        }
    }

    return Pair(hours, minutes)
}

fun main() {
    val examples = listOf(
        "1hr 58min",
        "1 hr 58 min",
        "1 h 58 m",
        "1hr",
        "1 hr",
        "1 h",
        "58min",
        "58 min",
        "58 m"
    )

    for (example in examples) {
        val (hours, minutes) = extractTimeValues(example)
        println("Input: $example, Hours: $hours, Minutes: $minutes")
    }
}

