package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.plusAssign
import kotlin.random.Random

enum class BakedGood(val cost: Int) {
  CAKE(15),
  PIE(10),
  CUPCAKE(5)
}

private val random = Random(546)
private val menu = listOf("🎂", "🍰", "🧁")

val orders = List(100_000) { menu.random(random) }

fun bake(item: String): BakedGood = when (item) {
  "🎂" -> BakedGood.CAKE
  "🍰" -> BakedGood.PIE
  "🧁" -> BakedGood.CUPCAKE
  else -> throw kotlin.IllegalArgumentException()
}

fun main() {
//  app1()
//  app2()
//  app3()
//  app4()
  app5()
}

@OptIn(ExperimentalAtomicApi::class)
fun app1() {
//  var total = 0
  val total = AtomicInt(0)
  runBlocking(Dispatchers.Default) {
    val baker1 = launch {
      orders.take(50_000).forEach { item ->
        val good = bake(item)
//        total += good.cost
        total += good.cost
      }
    }
    val baker2 = launch {
      orders.drop(50_000).forEach { item ->
        val good = bake(item)
//        total += good.cost
        total += good.cost
      }
    }
  }

//  println("Total income: $${String.format("%d", total)}")
  println("Total income: $${String.format("%d", total.load())}")
}

fun app2() {
  val total = MutableStateFlow(0)
  runBlocking(Dispatchers.Default) {
    val baker1 = launch {
      orders.take(50_000).forEach { item ->
        val good = bake(item)
        total.update { it + good.cost }
      }
    }
    val baker2 = launch {
      orders.drop(50_000).forEach { item ->
        val good = bake(item)
        total.update { it + good.cost }
      }
    }
  }

  println("Total income: $${String.format("%d", total.value)}")
}

fun app3() {
  var total = 0
  val mutex = Mutex()
  runBlocking(Dispatchers.Default) {
    val baker1 = launch {
      orders.take(50_000).forEach { item ->
        val good = bake(item)
        mutex.withLock {
          total += good.cost
        }
      }
    }
    val baker2 = launch {
      orders.drop(50_000).forEach { item ->
        val good = bake(item)
        mutex.withLock {
          total += good.cost
        }
      }
    }
  }

  println("Total income: $${String.format("%d", total)}")
}

fun app4() {
  var total = 0
  val synchronized = Dispatchers.Default.limitedParallelism(1)

  runBlocking(Dispatchers.Default) {
    val baker1 = launch {
      orders.take(50_000).forEach { item ->
        val good = bake(item)
        withContext(synchronized) {
          total += good.cost
        }
      }
    }
    val baker2 = launch {
      orders.drop(50_000).forEach { item ->
        val good = bake(item)
        withContext(synchronized) {
          total += good.cost
        }
      }
    }
  }

  println("Total income: $${String.format("%d", total)}")
}

fun app5() {
  val total = runBlocking(Dispatchers.Default) {
    val baker1 = async {
      orders.take(50_000).sumOf { item -> bake(item).cost }
    }
    val baker2 = async {
      orders.drop(50_000).sumOf { item -> bake(item).cost }
    }
    baker1.await() + baker2.await()
  }
  println("Total income: $${String.format("%d", total)}")
}