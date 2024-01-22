package dev.vengateshm.android_kotlin_compose_practice.multiple_screen_size

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    return WindowInfo(
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp,
        screenWidthType =
            when {
                configuration.screenWidthDp < 600 -> WindowInfo.WindowType.Compact
                configuration.screenWidthDp < 840 -> WindowInfo.WindowType.Medium
                else -> WindowInfo.WindowType.Expanded
            },
        screenHeightType =
            when {
                configuration.screenHeightDp < 480 -> WindowInfo.WindowType.Compact
                configuration.screenHeightDp < 900 -> WindowInfo.WindowType.Medium
                else -> WindowInfo.WindowType.Expanded
            },
    )
}

data class WindowInfo(
    val screenWidth: Dp,
    val screenHeight: Dp,
    val screenWidthType: WindowType,
    val screenHeightType: WindowType,
) {
    sealed class WindowType {
        object Compact : WindowType()

        object Medium : WindowType()

        object Expanded : WindowType()
    }
}
