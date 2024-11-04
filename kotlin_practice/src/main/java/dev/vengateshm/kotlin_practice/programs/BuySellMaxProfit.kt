package dev.vengateshm.kotlin_practice.programs

fun main() {
    val prices = intArrayOf(7, 1, 5, 3, 6, 4)
    println(maxProfit(prices))
}

fun maxProfit(prices: IntArray): Int {
    if (prices.isEmpty()) return 0

    var min = Int.MAX_VALUE
    var maxProfit = Int.MIN_VALUE
    for (price in prices) {
        if (price < min) {
            min = price
        } else {
            val profit = price - min
            if (profit > maxProfit) {
                maxProfit = profit
            }
        }
    }
    return maxProfit
}