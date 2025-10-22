package dev.vengateshm.compose_material3.api_kotlin.coroutines.flowOn

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NumberRepository {

  fun getNumbers(): Flow<Int> = flow {
    println("Repository emitting on: ${currentCoroutineContext()[CoroutineDispatcher]}")
    for (i in 1..5) {
      delay(500)
      println("Repository emitting: $i")
      emit(i)
    }
  }.flowOn(Dispatchers.IO)
}