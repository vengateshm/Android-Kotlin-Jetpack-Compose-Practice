package dev.vengateshm.android_kotlin_compose_practice.calculator

sealed class CalculatorOperation(val symbol: String) {
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Multiply : CalculatorOperation("X")
    object Divide : CalculatorOperation("/")
}
