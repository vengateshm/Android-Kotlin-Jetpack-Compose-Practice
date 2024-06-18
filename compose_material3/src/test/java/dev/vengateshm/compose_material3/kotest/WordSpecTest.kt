package dev.vengateshm.compose_material3.kotest

import io.kotest.core.Tag
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class WordSpecTest : WordSpec({
    isolationMode = IsolationMode.SingleInstance
    "Calculator" should {
        val calculator = Calculator()

        "test add".config(tags = setOf(Tag("Add"))) {
            this.testCase.config.tags.toString()
            println("Calculator instance inside ${this.testCase.name.testName}: $calculator")
            calculator.add(1, 2) shouldBe 3
        }

        "test subtract".config(tags = setOf(Tag("Subtract"))) {
            println("Calculator instance inside ${this.testCase.name.testName}: $calculator")
            calculator.subtract(2, 2) shouldBe 0
        }
    }
})

class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun subtract(a: Int, b: Int): Int {
        return a - b
    }
}