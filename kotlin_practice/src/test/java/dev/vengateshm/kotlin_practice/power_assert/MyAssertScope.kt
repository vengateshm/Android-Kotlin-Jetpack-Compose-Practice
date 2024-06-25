package dev.vengateshm.kotlin_practice.power_assert

interface MyAssertScope {
    fun assert(assertion: Boolean, message: (() -> String)? = null)
}

class MyAssertScopeImpl : MyAssertScope {
    val errors = mutableListOf<String>()
    override fun assert(assertion: Boolean, message: (() -> String)?) {
        if (!assertion) {
            errors.add(message?.invoke() ?: "Assertion failed")
        }
    }
}

fun <R> assertSoftly(block: MyAssertScope.() -> R): R {
    val scope = MyAssertScopeImpl()
    val result = scope.block()
    if (scope.errors.isNotEmpty()) {
        throw AssertionError(scope.errors.joinToString("\n"))
    }
    return result
}