package dev.vengateshm.kotlin_practice.std_library_samples

open class NormalClass {
    open fun normalClassFunction() {
        println("Normal class function")
    }
}

object ObjectClass : NormalClass() {
    override fun normalClassFunction() {
        super.normalClassFunction()
        println("ObjectClass normalClassFunction")
    }

    fun normalClassFunctionCallSuper() {
        super.normalClassFunction()
    }
}

fun main() {
    ObjectClass.normalClassFunction()
    ObjectClass.normalClassFunctionCallSuper()
}