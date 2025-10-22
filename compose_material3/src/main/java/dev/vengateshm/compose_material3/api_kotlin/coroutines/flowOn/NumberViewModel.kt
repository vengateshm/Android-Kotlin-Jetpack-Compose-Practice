package dev.vengateshm.compose_material3.api_kotlin.coroutines.flowOn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class NumberViewModel : ViewModel() {
  private val repository = NumberRepository()
  val numbersFlow = repository.getNumbers()

  init {
    viewModelScope.launch {
      println("ViewModel collecting on: ${this.coroutineContext[CoroutineDispatcher]}")
      numbersFlow.collect {
        println("ViewModel received: $it")
      }
    }
  }
}