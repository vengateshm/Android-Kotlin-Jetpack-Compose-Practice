package dev.vengateshm.kotlin_practice.clean_code_tips

// Avoid using constants without name
fun calculate1(transactionType: Int) {
    var price = 10.0
    if (transactionType == 1) {
        price *= 1.1
    }
}

// Better way
const val TAXABLE_TRANSACTION_TYPE = 1
const val TAX_MULTIPLE = 1.1

fun calculate(transactionType: Int, order: Order) {
    var price = order.price
    if (transactionType == TAXABLE_TRANSACTION_TYPE) {
        price *= TAX_MULTIPLE
    }
}

data class Order(val price: Double)

fun main() {
    calculate1(1)
}