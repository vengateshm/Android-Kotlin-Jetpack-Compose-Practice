package dev.vengateshm.android_kotlin_compose_practice.system_bars_hide_show

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

// Usage
@Composable
fun SystemBarHideShow() {
    var showSystemBar by remember {
        mutableStateOf(true)
    }

    SystemBarVisibility(showSystemBar)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            showSystemBar = !showSystemBar
        }) {
            Text(text = "Hide/Show System Bars")
        }
    }
}

@Composable
fun SystemBarVisibility(showSystemBar: Boolean) {
    val view = LocalView.current
    val window = (view.context as Activity).window
    val insetsController = WindowCompat.getInsetsController(window, view)
    if (!view.isInEditMode) {
        if (showSystemBar) {
            insetsController.apply {
                show(WindowInsetsCompat.Type.systemBars())
            }
        } else {
            insetsController.apply {
                hide(WindowInsetsCompat.Type.systemBars())
                // This flag makes system bars visible
                // when swiped on top or bottom of the screen
                systemBarsBehavior =
                    WindowInsetsControllerCompat
                        .BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}

