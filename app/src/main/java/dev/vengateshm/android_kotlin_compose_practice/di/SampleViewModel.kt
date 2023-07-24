package dev.vengateshm.android_kotlin_compose_practice.di

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SampleViewModel @AssistedInject constructor(
    private val sampleRepo: SampleRepo,
    @Assisted private val text: String,
) : ViewModel() {

    var clickCount = mutableStateOf(0)
        private set

    fun updateClickCount() {
        clickCount.value = clickCount.value + 1
    }

    init {
        Log.i("SampleViewModel", text)
    }

    @AssistedFactory
    interface Factory {
        fun create(text: String): SampleViewModel
    }

    companion object {
        fun provideSampleViewModelFactory(
            factory: Factory,
            text: String,
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(text) as T
                }
            }
        }
    }
}