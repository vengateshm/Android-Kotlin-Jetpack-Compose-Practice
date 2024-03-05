package dev.vengateshm.kotlin_practice.std_library_samples

class Car {
    init {
        println("Car initialized")
    }

    companion object {
        init {
            println("Car companion initialized")
        }

        val name="BMW"
        fun printName(){
            println("Car name : $name")
        }
    }
}

fun main() {
    // Car - Without companion object it throws compiler error
    Car.printName()
    Car.printName()
}