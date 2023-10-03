package dev.vengateshm.kotlin_practice

import dev.vengateshm.kotlin_practice.testing.PrivateMethodClass
import org.junit.Assert
import org.junit.Test

class PrivateMethodClassTest {
    // Comment
    // use JuintPlatform in build.gradle to run this test

    val privateMethodClass = PrivateMethodClass()

    @Test
    fun testPrivateMethod() {
        val method = privateMethodClass.javaClass.getDeclaredMethod(
            "privateMethod",
            String::class.java,
            String::class.java
        )
        method.isAccessible = true
        val result = method.invoke(privateMethodClass,"Private","Method")
        Assert.assertEquals("Private Method", result)
    }
}