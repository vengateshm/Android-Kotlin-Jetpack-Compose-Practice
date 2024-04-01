package dev.vengateshm.kotlin_practice.programs

class FormatSocialStatUseCase {
    fun execute(number: Long): String {
        if (number < 1_000) return number.toString()

        val suffix = when {
            number >= 1_000_000 -> "M"
            else -> "K"
        }

        return when {
            number in 1_000..999_999 -> String.format("%.1f%s", number / 1000.0, suffix)
            else -> String.format("%.1f%s", number / 1_000_000.0, suffix)
        }.replace(".0", "")
    }
}

fun main() {
    println(FormatSocialStatUseCase().execute(999))
    println(FormatSocialStatUseCase().execute(5642))
    println(FormatSocialStatUseCase().execute(100_000))
    println(FormatSocialStatUseCase().execute(1_000_000))
    println(FormatSocialStatUseCase().execute(10_000_000))
}