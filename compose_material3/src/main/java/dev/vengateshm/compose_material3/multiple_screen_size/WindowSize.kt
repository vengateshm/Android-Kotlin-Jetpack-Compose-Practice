package dev.vengateshm.compose_material3.multiple_screen_size

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

enum class WindowType {
    Compact,
    Medium,
    Expanded
}

data class WindowSize(
    val width: WindowType,
    val height: WindowType
)

@Composable
fun getWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current

    return WindowSize(
        width = when {
            configuration.screenWidthDp < 600 -> WindowType.Compact
            configuration.screenWidthDp < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        },
        height = when {
            configuration.screenHeightDp < 600 -> WindowType.Compact
            configuration.screenHeightDp < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        }
    )
}