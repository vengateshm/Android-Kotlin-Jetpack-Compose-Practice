package dev.vengateshm.android_kotlin_compose_practice.app_shortcuts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AppShortcutViewModel : ViewModel() {
    var shortcutType by mutableStateOf<ShortcutType?>(null)
        private set

    fun onShortCutClicked(type: ShortcutType) {
        shortcutType = type
    }
}

enum class ShortcutType {
    STATIC, DYNAMIC, PINNED
}