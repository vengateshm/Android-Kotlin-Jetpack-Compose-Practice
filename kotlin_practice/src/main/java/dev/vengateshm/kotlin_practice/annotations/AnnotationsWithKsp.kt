package dev.vengateshm.kotlin_practice.annotations

import dev.vengateshm.ksp_samples.annotations.Decorator
import dev.vengateshm.ksp_samples.annotations.GsonSerialize
import dev.vengateshm.ksp_samples.annotations.MyAnnotation
import dev.vengateshm.ksp_samples.annotations.MySampleAnnotation

@MySampleAnnotation("MySampleAnnotation in class")
class AnnotationsWithKsp {

}

@MyAnnotation
class Named {

}

@GsonSerialize
data class Vehicle(val make: String)

@Decorator
interface Accessories {
    fun getDescription(): String
    fun price(): Double
}

class MusicSystem(private val accessories: Accessories) : Accessories {
    override fun getDescription(): String {
        return "${accessories.getDescription()}\nWith Music system worth ${price()}"
    }

    override fun price(): Double {
        return accessories.price() + 15000.0
    }
}

class Car : Accessories {
    override fun getDescription(): String {
        return "Create a car worth ${price()}"
    }

    override fun price(): Double {
        return 950000.0
    }
}

fun main() {
    //NamedGenerated().printName()
    AnnotationsWithKsp()
    val vehicle = Vehicle(make = "Honda")
    //println(vehicle.toJson())

    val car = DecorateWithAccessories(MusicSystem(Car()))
    println(car.getDescription())
    println(car.price())
}