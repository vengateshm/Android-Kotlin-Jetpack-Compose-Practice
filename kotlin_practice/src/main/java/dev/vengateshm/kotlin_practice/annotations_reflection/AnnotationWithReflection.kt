package dev.vengateshm.kotlin_practice.annotations_reflection

import kotlin.reflect.full.findAnnotation

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SampleAnnotation(val desc: String)

@SampleAnnotation("Sample annotation in class")
class AnnotationWithReflection {
    @SampleAnnotation("sample annotation in function")
    fun demo() {

    }
}

fun main() {
    val clazz = AnnotationWithReflection::class
    val findAnnotation = clazz.findAnnotation<SampleAnnotation>()
    println(findAnnotation?.desc)

    val demoFun = clazz.members.find { it.name == "demo" }
    val funAnnotation = demoFun?.findAnnotation<SampleAnnotation>()
    println(funAnnotation?.desc)
}