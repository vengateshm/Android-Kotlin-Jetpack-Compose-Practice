package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

fun cpuIntensiveOperation1() {
  println("Starting cpuIntensiveOperation1 on ${Thread.currentThread().name}")
  Thread.sleep(100)
  println("Finished cpuIntensiveOperation1")
}

fun cpuIntensiveOperation2() {
  println("Starting cpuIntensiveOperation2 on ${Thread.currentThread().name}")
  Thread.sleep(100)
  println("Finished cpuIntensiveOperation2")
}

fun cpuIntensiveOperation3() {
  println("Starting cpuIntensiveOperation3 on ${Thread.currentThread().name}")
  Thread.sleep(100)
  println("Finished cpuIntensiveOperation3")
  throw IllegalStateException("Simulated failure in cpuIntensiveOperation3")
}

fun blockingOperation1() {
  println("Starting blockingOperation1 on ${Thread.currentThread().name}")
  Thread.sleep(200)
  println("Finished blockingOperation1")
}

fun blockingOperation2() {
  println("Starting blockingOperation2 on ${Thread.currentThread().name}")
  Thread.sleep(200)
  println("Finished blockingOperation2")
}

fun blockingOperation3() {
  println("Starting blockingOperation3 on ${Thread.currentThread().name}")
  Thread.sleep(200)
  println("Finished blockingOperation3")
}

suspend fun cpuIntensiveOperations() =
  withContext(Dispatchers.Default) {
    cpuIntensiveOperation1()
    yield()
    cpuIntensiveOperation2()
    yield()
//    if(!isActive) return@withContext
    cpuIntensiveOperation3()
  }

suspend fun blockingOperations() =
  withContext(Dispatchers.IO) {
    blockingOperation1()
    yield() // Give other coroutines on Dispatchers.IO a chance to run
    blockingOperation2()
    yield() // Give other coroutines on Dispatchers.IO a chance to run
    blockingOperation3()
  }

suspend fun main() {
  println("Starting CPU intensive operations demo...")
  cpuIntensiveOperations()
  println("CPU intensive operations demo finished.\n")

  println("Starting blocking operations demo...")
  blockingOperations()
  println("Blocking operations demo finished.")
}