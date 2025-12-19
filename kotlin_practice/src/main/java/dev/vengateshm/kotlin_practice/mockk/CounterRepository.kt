package dev.vengateshm.kotlin_practice.mockk

import kotlinx.coroutines.delay

class CounterRepository {

  private var count: Int = 0

  fun getCount(): Int = count

  fun increment() {
    count++
  }

  fun decrement() {
    count--
  }

  suspend fun reset() {
    count = 0
  }

  suspend fun saveCountInDb(count: Int) {
    this.count = count
  }

  suspend fun loadFromNetwork(callback: suspend (Int) -> Unit) {
    delay(1000)
    callback(NETWORK_RESPONSE)
  }

  companion object {
    const val NETWORK_RESPONSE = 200
  }
}
