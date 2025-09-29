package dev.vengateshm.compose_material3.api_kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

fun blockingCode() {
  (1..50_000_000).map { it * it }
  println("Blocking code finished!")
}

suspend fun suspendingCode() = withContext(Dispatchers.Default) {
  (1..50_000_000).map { it * it }
  println("Suspending code finished!")
}

fun main() {
//  threadCode()
  coroutineCode()
}

fun threadCode() {
  println("Start!")
  thread { blockingCode() }
  println("End!")
}

fun coroutineCode() {
  runBlocking {
    CoroutineScope(Dispatchers.Default).launch {
      launch { suspendingCode() }
      launch { suspendingCode() }
    }.join()
  }
}
