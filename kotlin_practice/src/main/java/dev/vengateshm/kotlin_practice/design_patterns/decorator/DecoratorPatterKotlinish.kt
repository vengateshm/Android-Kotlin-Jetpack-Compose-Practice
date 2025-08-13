package dev.vengateshm.kotlin_practice.design_patterns.decorator

import java.util.UUID
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun interface KLogger {
  fun log(message: String)
}

val consoleLogger = KLogger { print(it);println() }

fun KLogger.withUniqueId() = KLogger { log("[${UUID.randomUUID()}] $it") }

@OptIn(ExperimentalTime::class)
fun KLogger.withDateTime(clock: Clock = Clock.System) = KLogger { log("{${clock.now()}} $it") }
fun KLogger.withThreadName() = KLogger { log("$it (on ${Thread.currentThread().name} thread)") }

@OptIn(ExperimentalTime::class)
fun main() {
  consoleLogger.withDateTime().withThreadName().withUniqueId().log("Application started")
}