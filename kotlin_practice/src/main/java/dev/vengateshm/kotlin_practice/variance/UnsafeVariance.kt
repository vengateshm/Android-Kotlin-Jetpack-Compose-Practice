package dev.vengateshm.kotlin_practice.variance

// Covariance
class Container<out T>(private var item: T) {
    // Trying to consume T
    fun contains(other: @UnsafeVariance T): Boolean {
        return other == this.item
    }
}

// Contravariance
class MailBox<in T>(private var item: T) {
    // Trying to produce T
    fun dispatch(): @UnsafeVariance T {
        return item
    }
}


fun main() {
    Container("hello").contains("")
    MailBox("hello").dispatch()
}