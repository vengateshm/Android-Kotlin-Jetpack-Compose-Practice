package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay

suspend fun requestDatabaseChanges() {
  try {
    updateDatabase()
  } finally {
    withContext(NonCancellable) {
      cleanUpDatabase()
    }
  }
}

suspend fun updateDatabase() {
  delay(100L)
}

suspend fun cleanUpDatabase() {
  delay(100L)
}