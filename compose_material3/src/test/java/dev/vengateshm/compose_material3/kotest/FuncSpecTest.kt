package dev.vengateshm.compose_material3.kotest

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.UUID

class FuncSpecTest : FunSpec({
    test("addition") {
        println(UUID.randomUUID())
        1 + 2 shouldBe 3
    }

    test("subtraction") {
        println(UUID.randomUUID())
        1 - 2 shouldBe -1
    }

    test("multiplication").config(enabled = false) {
        println(UUID.randomUUID())
        3 * 2 shouldBe 6
    }

    test("!division") {
        // This will be ignored
        println(UUID.randomUUID())
        6 % 2 shouldBe 3
    }

    test("f:square") {
        // Only this runs and other tests ignored
        println(UUID.randomUUID())
        3 * 3 shouldBe 9
    }
})