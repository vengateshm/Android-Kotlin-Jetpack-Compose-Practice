package dev.vengateshm.navigation3_sample.notes_app.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedAuthViewModel : ViewModel() {
  private val _counter = MutableStateFlow(0)
  val counter = _counter.asStateFlow()

  fun bump() {
    _counter.value++
  }

  init {
    println("INITIALIZING SharedAuthViewModel")
  }

  override fun onCleared() {
    super.onCleared()
    println("CLEARING SharedAuthViewModel")
  }
}