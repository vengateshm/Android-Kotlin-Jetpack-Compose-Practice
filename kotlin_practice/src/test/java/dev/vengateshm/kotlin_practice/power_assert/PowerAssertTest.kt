package dev.vengateshm.kotlin_practice.power_assert

import kotlin.test.Ignore
import kotlin.test.Test

class PowerAssertTest {
    @Test
    @Ignore
    fun testFunction() {
        val hello = "Hello"
        val world = "world!"
        assert(hello.length == world.substring(1, 4).length) { "Incorrect length" }
    }

    data class Person(val name: String, val age: Int)

    @Ignore
    @Test
    fun testComplexAssertion() {
        val person = Person("Alice", 10)
        val isValidName = person.name.startsWith("A") && person.name.length > 3
        val isValidAge = person.age in 21..28
        assert(isValidName && isValidAge)
    }

    @Ignore
    @Test
    fun testRequireFunction() {
        val value = "NA"
        require(value != "NA") { "Value should not be empty" }
    }

    data class Employee(val name: String, val age: Int, val salary: Int)

    @Test
    @Ignore
    fun `test employees data`() {
        val employees = listOf(
            Employee("Alice", 30, 60000),
            Employee("Bob", 45, 80000),
            Employee("Charlie", 55, 40000),
            Employee("Dave", 150, 70000)
        )

        assertSoftly {
            for (employee in employees) {
                assert(employee.age < 100) { "${employee.name} has an invalid age: ${employee.age}" }
                assert(employee.salary > 50000) { "${employee.name} has an invalid salary: ${employee.salary}" }
            }
        }
    }
}