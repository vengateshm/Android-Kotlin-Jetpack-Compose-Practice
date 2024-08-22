package dev.vengateshm.kotlin_practice.design_patterns.decorator

import kotlinx.datetime.Clock
import java.util.UUID

fun interface KLogger {
    fun log(message: String)
}

val consoleLogger = KLogger { print(it);println() }

fun KLogger.withUniqueId() = KLogger { log("[${UUID.randomUUID()}] $it") }
fun KLogger.withDateTime(clock: Clock = Clock.System) = KLogger { log("{${clock.now()}} $it") }
fun KLogger.withThreadName() = KLogger { log("$it (on ${Thread.currentThread().name} thread)") }

fun main() {
    consoleLogger.withDateTime().withThreadName().withUniqueId().log("Application started")
}