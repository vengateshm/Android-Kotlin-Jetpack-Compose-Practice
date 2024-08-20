package dev.vengateshm.kotlin_practice.utils

import org.junit.jupiter.api.DisplayNameGenerator
import java.lang.reflect.Method

class TestDisplayNameGenerator : DisplayNameGenerator {
    private val default = DisplayNameGenerator.Standard()
    override fun generateDisplayNameForClass(testClass: Class<*>?): String {
        return default.generateDisplayNameForClass(testClass)
    }

    override fun generateDisplayNameForNestedClass(nestedClass: Class<*>?): String {
        return default.generateDisplayNameForNestedClass(nestedClass)
    }

    override fun generateDisplayNameForMethod(testClass: Class<*>?, testMethod: Method?): String {
        val isTodo = testMethod?.isAnnotationPresent(ToDo::class.java) ?: false
        return if (isTodo) default.generateDisplayNameForMethod(testClass, testMethod) + " // TODO"
        else default.generateDisplayNameForMethod(testClass, testMethod)
    }
}