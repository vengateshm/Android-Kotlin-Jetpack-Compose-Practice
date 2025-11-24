package dev.vengateshm.kotlin_practice.std_library_samples

import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() {
  val executor = Executors.newFixedThreadPool(2)
//  val queue: BlockingQueue<Int> = ArrayBlockingQueue<Int>(10)
//  val queue: BlockingQueue<Int> = LinkedBlockingQueue<Int>(80)
  val queue: BlockingQueue<Int> = PriorityBlockingQueue()

  executor.submit(getProducerThread(queue, 100))
  executor.submit(getProducerThread(queue, 100))

  executor.awaitTermination(2, TimeUnit.SECONDS)
  println("Queue size after processing: ${queue.size}")
}

fun getProducerThread(
  queue: BlockingQueue<Int>,
  nbrOfElementsToProduce: Int,
): Runnable {
  return Runnable {
    try {
      repeat(nbrOfElementsToProduce) {
        val randomInt = Random.nextInt(1, 100)
        queue.put(randomInt)
        println("Produced: $randomInt")
      }
    } catch (e: InterruptedException) {
      Thread.currentThread().interrupt()
    }
  }
}

fun getConsumerThread(
  queue: BlockingQueue<Int>,
  nbrOfElementsToProduce: Int,
): Runnable {
  return Runnable {
    try {
      repeat(nbrOfElementsToProduce) {
        val randomInt = Random.nextInt(1, 100)
        queue.take()
        println("Consumed: $randomInt")
      }
    } catch (e: InterruptedException) {
      Thread.currentThread().interrupt()
    }
  }
}