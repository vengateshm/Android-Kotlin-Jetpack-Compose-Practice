package dev.vengateshm.kotlin_practice

import strikt.api.Assertion
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.contains
import strikt.assertions.hasLength
import strikt.assertions.isA
import strikt.assertions.isGreaterThan
import strikt.assertions.isLessThan
import strikt.assertions.isNotBlank
import strikt.assertions.isNotEmpty
import strikt.assertions.isNotEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.map
import strikt.assertions.startsWith
import kotlin.test.Test

class StriktTests {
    @Test
    fun testSimple() {
        expectThat(1)
            .isGreaterThan(0)
    }

    @Test
    fun testSimple1() {
        expect {
            that("Hello").isNotEmpty()
            that(3).isNotEqualTo(31)
        }
    }

    @Test
    fun testSimple2() {
        expectThat("Start") {
            isNotEmpty()
            isNotBlank()
            isNotNull()
            startsWith("S")
            hasLength(5)
        }
    }

    @Test
    fun testSimple3() {
        val subject: Any? = "Start"
        expectThat(subject)
            .isNotNull()
            .isA<String>()
            .isNotBlank()
    }

    @Test
    fun testSimple4() {
        val items = listOf("Apple", "Orange", "Berry")
        expectThat(items)
            .isNotEmpty()
            .contains("Orange")
    }

    @Test
    fun testSimple5() {
        val students = listOf(
            Student("Alice", 20),
            Student("Bob", 22),
            Student("Charlie", 21),
            Student("David", 23),
        )

        expectThat(students)
            .map { it.age }
            .all {
                isGreaterThan(18)
                isLessThan(30)
            }
    }

    @Test
    fun testSimple6() {
        expectThat("string")
            .isDigitsOnly()
    }

    @Test
    fun testSimple7() {
        expectThat("543")
            .isDigitsOnly()
    }

    @Test
    fun testSimple8() {
        val student = Student("Alice", 20)
        expectThat(student) {
            get { name }.isNotBlank().isNotEmpty()
            get(Student::age).isGreaterThan(18)
        }
    }
}

data class Student(val name: String, val age: Int)

fun Assertion.Builder<String>.isDigitsOnly() =
    assert("is digits only") {
        val isAllDigits = it.all { it.isDigit() }
        if (isAllDigits) pass() else fail()
    }