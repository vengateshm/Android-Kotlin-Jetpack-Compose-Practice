package dev.vengateshm.compose_material3.kotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringSpecTest : StringSpec({
    "test 1".config(invocations = 3) {
        1 + 2 shouldBe 3
    }

    "test 2" {
        2 - 2 shouldBe 0
    }
})