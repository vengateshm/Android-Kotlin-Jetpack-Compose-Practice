package dev.vengateshm.android_kotlin_compose_practice.manual_di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <VM> viewModelFactory(initializer: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializer() as T
        }
    }