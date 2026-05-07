package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

data class GroceryItem(
  val label: String,
  val priceInCents: Int,
)

class OutOfStockException(label: String) :
  Exception("Item out of stock: $label")

suspend fun findOnShelf(label: String): GroceryItem {
  delay(200)

  return when (label) {
    "🥛" -> GroceryItem("🥛 Milk", 339)
    "🍉" -> GroceryItem("🍉 Watermelon", 599)
    else -> throw OutOfStockException(label)
  }
}

suspend fun localTryCatchExample() {
  flow {
    emit("🥛")
    emit("🍊")
    emit("🍉")
  }
    .transform { emoji ->
      try {
        emit(findOnShelf(emoji))
      } catch (e: OutOfStockException) {
        println("Local catch: ${e.message}")
        emit(
          GroceryItem(
            "❓ Substitute Item",
            0,
          ),
        )
      }
    }
    .collect {
      println("Cart: ${it.label}")
    }
}

suspend fun catchOperatorExample() {
  flow {
    emit(1)
    emit(2)

    throw RuntimeException(
      "Something failed upstream",
    )
  }
    .map {
      it * 10
    }
    .catch { e ->
      if (e is OutOfStockException) {
        emit(-1)
      }
      throw e
    }
    .catch {
      println("Not found")
    }
    .collect {
      println("Collected: $it")
    }
}

suspend fun outerTryCatchExample() {

  val numbers: Flow<Int> = flow {
    emit(1)
    emit(2)
    emit(3)
  }

  try {

    numbers.collect {

      println("Collected: $it")

      if (it == 2) {
        throw IllegalStateException(
          "Collector failed on $it",
        )
      }
    }

  } catch (e: Exception) {

    println("Outer try/catch: ${e.message}")
  }
}


fun main() = runBlocking {
  println("=== Local try/catch ===")
  localTryCatchExample()

  println()
  println("=== catch operator ===")
  catchOperatorExample()

  println()
  println("=== outer try/catch ===")
  outerTryCatchExample()
}