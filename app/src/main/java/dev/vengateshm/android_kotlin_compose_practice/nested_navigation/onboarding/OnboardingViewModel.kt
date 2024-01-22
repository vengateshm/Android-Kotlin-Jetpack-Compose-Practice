package dev.vengateshm.android_kotlin_compose_practice.nested_navigation.onboarding

import androidx.lifecycle.ViewModel

class OnboardingViewModel : ViewModel() {
    private val _screenVisitCount = mutableMapOf<String, Int>()
    val screenVisitCount: Map<String, Int> = _screenVisitCount

    fun updateScreenVisitCount(name: String) {
        if (_screenVisitCount[name] == null) {
            _screenVisitCount[name] = 1
        } else {
            _screenVisitCount[name] = _screenVisitCount.getValue(name) + 1
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("onCleared called")
    }
}
