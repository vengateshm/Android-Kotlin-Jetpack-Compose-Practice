package dev.vengateshm.compose_material3.kotest

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.data.blocking.forAll
import java.util.UUID

class FailFastTest : FunSpec({
    failfast = true

    test("addition") {
        println(UUID.randomUUID())
        1 + 2 shouldBe 3
    }

    test("subtraction") {
        println(UUID.randomUUID())
        1 - 2 shouldBe 1
    }

    test("multiplication").config(enabled = false) {
        println(UUID.randomUUID())
        3 * 2 shouldBe 6
    }
})