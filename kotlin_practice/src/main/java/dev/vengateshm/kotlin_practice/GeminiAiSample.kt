package dev.vengateshm.kotlin_practice

import java.math.BigDecimal
import java.text.NumberFormat

fun formatPrice(price: Long): String {
    if (price < 0.0) {
        throw IllegalArgumentException("Price must be non-negative")
    }

    return NumberFormat.getCurrencyInstance().format(
        BigDecimal(price).movePointLeft(2)
    )
}

enum class Currency(val currencyName: String) {
    USD("United States Dollar"),
    EUR("Euro"),
    NOK("Norwegian Krone")
}

fun Currency.toString(price: Long): String {
    return when (this) {
        Currency.USD -> "$" + formatPrice(price)
        Currency.EUR -> "€" + formatPrice(price)
        Currency.NOK -> "kr" + formatPrice(price)
    }
}