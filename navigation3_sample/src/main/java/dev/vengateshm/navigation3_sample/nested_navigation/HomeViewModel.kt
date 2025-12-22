package dev.vengateshm.navigation3_sample.nested_navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
  var num2 by mutableIntStateOf(0)
    private set

  fun incrementNumber2() {
    num2++
  }

  init {
    println("INITIALIZING HomeViewModel")
  }

  override fun onCleared() {
    super.onCleared()
    println("CLEARING HomeViewModel")
  }
}