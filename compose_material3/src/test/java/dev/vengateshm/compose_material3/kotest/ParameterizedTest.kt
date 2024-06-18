package dev.vengateshm.compose_material3.kotest

import io.kotest.core.spec.style.WordSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import kotlin.math.max

class ParameterizedTest : WordSpec({
    "f:something" should {
        listOf("a", "b", "c").forEach {
            "test length of $it" {
                it.length shouldBe 1
            }
        }
        "maximum of two numbers" {
            forAll(
                row(1, 5, 5),
                row(1, 0, 1),
                row(0, 0, 0)
            ) { a, b, max ->
                max(a, b) shouldBe max
            }
        }
    }

    "better" should {
        listOf("a", "b", "c").forEach {
            "test length of $it" {
                it.length shouldBe 1
            }
        }
        "maximum of two numbers" {
            forAll(
                row(1, 5, 5),
                row(1, 0, 1),
                row(0, 0, 0)
            ) { a, b, max ->
                max(a, b) shouldBe max
            }
        }
    }
})