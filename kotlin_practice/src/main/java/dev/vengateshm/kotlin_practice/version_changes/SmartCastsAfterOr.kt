package dev.vengateshm.kotlin_practice.version_changes

// Smart casts after ||
interface Status {
    fun notify(msg: String) = println(msg)
}

interface Ordered : Status
interface Shipped : Status
interface InTransit : Status
interface Delivered : Status

fun process(orderStatus: Any) {
    if (orderStatus is Shipped || orderStatus is InTransit) {
        orderStatus.notify("Your order has been shipped!")
    }
}

fun main() {
    process(object : Shipped {})
    process(object : InTransit {})
}