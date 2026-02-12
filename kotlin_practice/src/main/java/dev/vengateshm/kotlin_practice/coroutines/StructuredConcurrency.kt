package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  val scope1 = CoroutineScope(Dispatchers.Default)

  val parentJob = scope1.launch {
    val childJob = launch {
      val grandChildJob = launch {
        delay(5000)
        throw RuntimeException("OOps")
        println("Grand child job finished!")
      }

      val grandChildJob2 = launch {
        delay(6000)
        println("Grand child job2 finished!")
      }
    }
    println("Parent job reached its end")
  }

//  scope1.launch {
//    delay(3000)
//    parentJob.cancel()
//  }

  runBlocking {
    parentJob.join()
    println("Finishing...")
  }
}