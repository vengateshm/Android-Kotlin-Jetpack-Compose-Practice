package dev.vengateshm.kotlin_practice.utils

import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler
import kotlin.jvm.optionals.getOrNull

class ToDoTestExecutionFailureHandler : TestExecutionExceptionHandler, AfterEachCallback {
    override fun handleTestExecutionException(context: ExtensionContext, throwable: Throwable?) {
        val element = context.element.getOrNull() ?: return
        val isToDo = element.isAnnotationPresent(ToDo::class.java)
        if (isToDo) {
            Assumptions.abort<Nothing>("Test was marked as ToDo")
        } else {
            throw throwable!!
        }
    }

    override fun afterEach(context: ExtensionContext) {
        val element = context.element.getOrNull() ?: return
        if (element.isAnnotationPresent(ToDo::class.java) && context.executionException.isEmpty) {
            error("Test passed; ToDo annotation still present: Please remove the @ToDo")
        }
    }
}