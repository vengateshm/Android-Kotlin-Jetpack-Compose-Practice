package dev.vengateshm.kotlin_practice.dsl_example

fun main() {
    val c = client {
        firstName = "Vengatesh"
        lastName = "M"

        twitter {
            handle = "@vengateshm"
        }

        company {
            name = "ABC"
            city = "Jersey"
        }

        dob = 24 July 2023
    }

    println("$c")
}