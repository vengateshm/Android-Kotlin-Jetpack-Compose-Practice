package dev.vengateshm.kotlin_practice.design_patterns.decorator

import kotlinx.datetime.Clock
import java.util.UUID

// Component
interface Logger {
    fun log(message: String)
}

class ConsoleLoggerPlain(
    private val includeDateTime: Boolean = false,
    private val includeThreadName: Boolean = false,
    private val includeUniqueId: Boolean = true,
    private val clock: Clock
) : Logger {
    override fun log(message: String) {
        if (includeDateTime) print("[${clock.now()}]")
        if (includeUniqueId) print("{${UUID.randomUUID()}} ")
        print(message)
        if (includeThreadName) print(" (on ${Thread.currentThread().name} thread)")
        println()
    }
}

// Concrete component
class ConsoleLogger : Logger {
    override fun log(message: String) {
        print(message)
        println()
    }
}

abstract class LoggerDecorator(val logger: Logger) : Logger

// Concrete decorators
class UniqueIdLogger(logger: Logger) : LoggerDecorator(logger) {
    override fun log(message: String) = logger.log("{${UUID.randomUUID()}} $message")
}

class ThreadNameLogger(logger: Logger) : LoggerDecorator(logger) {
    override fun log(message: String) =
        logger.log("$message (on ${Thread.currentThread().name} thread)")
}

class DateTimeLogger(logger: Logger, private val clock: Clock) : LoggerDecorator(logger) {
    override fun log(message: String) = logger.log("[${clock.now()}] $message")
}

fun main() {
    val consoleLogger = ConsoleLogger()
    DateTimeLogger(
        UniqueIdLogger(ThreadNameLogger(consoleLogger)),
        Clock.System
    ).log("Application started")

    ConsoleLoggerPlain(clock = Clock.System).log("Application started")
}
