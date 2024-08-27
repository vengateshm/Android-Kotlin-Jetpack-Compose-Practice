package dev.vengateshm.kotlin_practice.annotations

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

fun main() {
    NamedGenerated().printName()
    AnnotationsWithKsp()
    val vehicle = Vehicle(make = "Honda")
    println(vehicle.toJson())
}