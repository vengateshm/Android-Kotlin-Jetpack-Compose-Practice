package dev.vengateshm.xml_kotlin.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
inline fun <reified V : ViewModel> Fragment.getViewModel(crossinline viewModelInitializer: () -> V): V {
    val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModelInitializer() as T
        }
    }
    return ViewModelProvider(this, factory)[V::class.java]
}

@Suppress("UNCHECKED_CAST")
inline fun <reified V : ViewModel> Fragment.getViewModelWithSavedState(
    key: String? = null, defaultArg: Bundle? = null,
    crossinline viewModelInitializer: (SavedStateHandle) -> V,
): V {
    val factory = object :
        AbstractSavedStateViewModelFactory(this@getViewModelWithSavedState, defaultArg) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle,
        ): T {
            return viewModelInitializer(handle) as T
        }
    }
    return if (key == null) {
        ViewModelProvider(this, factory)[V::class.java]
    } else {
        ViewModelProvider(this, factory)[key, V::class.java]
    }
}