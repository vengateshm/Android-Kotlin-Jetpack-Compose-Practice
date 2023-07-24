package dev.vengateshm.android_kotlin_compose_practice.hilt_dependency_scopes

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionTimer: SessionTimer,
) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
    }
}