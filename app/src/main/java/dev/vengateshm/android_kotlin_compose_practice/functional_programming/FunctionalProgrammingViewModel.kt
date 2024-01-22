package dev.vengateshm.android_kotlin_compose_practice.functional_programming

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FunctionalProgrammingViewModel : ViewModel() {
    var result by mutableStateOf<NetworkOperation<List<String>>>(NetworkOperation.None())

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            result = NetworkOperation.Loading()
            delay(5_000L)
            result = NetworkOperation.Success(listOf("Tiramisu", "Honey Cake", "Donut "))
        }
    }
}
