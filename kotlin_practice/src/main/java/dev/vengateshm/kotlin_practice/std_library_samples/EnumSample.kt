package dev.vengateshm.kotlin_practice.std_library_samples

enum class BookingStatus(val code: Int) {
    Pending(0) {
        override fun toString(): String {
            return "{\"name\":\"${this.name}\",\"code\":\"${this.code}\"}"
        }
    },
    Complete(1) {
        override fun toString(): String {
            return "{\"name\":\"${this.name}\",\"code\":\"${this.code}\"}"
        }
    },
    Cancelled(2) {
        override fun toString(): String {
            return "{\"name\":\"${this.name}\",\"code\":\"${this.code}\"}"
        }
    }
}

fun main() {
    BookingStatus.entries.forEach {
        println(it.toString())
    }
}