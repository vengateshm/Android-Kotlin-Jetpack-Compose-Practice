package dev.vengateshm.compose_material3.kotest

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class AnnotationSpecTest : AnnotationSpec() {
    @Test
    fun test1() {
        1 + 2 shouldBe 3
    }

    @Ignore
    @Test
    fun test2() {
        2 - 2 shouldBe 0
    }
}