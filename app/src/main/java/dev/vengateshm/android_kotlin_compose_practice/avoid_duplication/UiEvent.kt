package dev.vengateshm.android_kotlin_compose_practice.avoid_duplication

sealed class UiEvent {
    data class Error(val message: String) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
}
