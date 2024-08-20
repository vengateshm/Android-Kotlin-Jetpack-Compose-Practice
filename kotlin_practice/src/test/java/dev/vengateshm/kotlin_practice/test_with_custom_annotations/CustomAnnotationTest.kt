package dev.vengateshm.kotlin_practice.test_with_custom_annotations

import dev.vengateshm.kotlin_practice.utils.ToDoTestExecutionFailureHandler
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomAnnotationTest {
    @Test
    fun add() {
        assertEquals(4, 2 + 2)
    }

    @Test
    //@ToDo
    @ExtendWith(ToDoTestExecutionFailureHandler::class)
    fun complexTest() {
        assertEquals("hello", complexLogic())
    }
}