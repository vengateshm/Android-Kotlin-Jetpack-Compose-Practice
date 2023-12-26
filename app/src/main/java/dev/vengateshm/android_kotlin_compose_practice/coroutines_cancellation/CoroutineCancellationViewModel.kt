package dev.vengateshm.android_kotlin_compose_practice.coroutines_cancellation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class CoroutineCancellationViewModel : ViewModel() {

    private val repository = CoroutineCancellationRepository()

    fun getData() {
        viewModelScope.launch {
            println("Before getData()")
//            repository.getDataNotThrown()
//            repository.getData()
            repository.getDataEither()
            println("After getData()")
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}