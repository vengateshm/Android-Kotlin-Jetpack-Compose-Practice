package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun runBlockingDelay(delay: Long) = runBlocking {
    delay(delay)
}