package dev.vengateshm.kotlin_practice.programs

import dev.vengateshm.kotlin_practice.extensions.println
import java.text.DecimalFormat
import java.util.Scanner
import kotlin.math.abs

const val ONE_BILLION = 1000000000
const val ONE_MILLION = 1000000
const val ONE_KILO = 1000


fun main() {

    val df = DecimalFormat("##.##")

    fun formatNumber(number: Long): String {
        val formatted = when {
            abs(number / ONE_BILLION) >= 1 -> {
                df.format(number.toDouble() / ONE_BILLION)+ "B"
            }
            abs(number / ONE_MILLION) >= 1 -> {
                df.format(number.toDouble() / ONE_MILLION) + "M"
            }
            abs(number / ONE_KILO) >= 1 -> {
                df.format(number.toDouble() / ONE_KILO) + "K"
            }
            else -> {
                number.toString()
            }
        }
        return formatted
    }

    val scanner = Scanner(System.`in`)
    "Enter a number".println()
    val number = scanner.nextLong()

    val result = formatNumber(number)
    println(result)
}