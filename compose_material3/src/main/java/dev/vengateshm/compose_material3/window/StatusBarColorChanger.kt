package dev.vengateshm.compose_material3.window

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

@Composable
fun StatusBarColorChanger(
    statusBarColor: Color?,
    navigationBarColor: Color?,
    isLightIcons: Boolean
) {
    val window = (LocalContext.current as Activity).window
    val view = LocalView.current

    SideEffect {
        statusBarColor?.let {
            window.statusBarColor = it.toArgb()
        }
        navigationBarColor?.let {
            window.navigationBarColor = it.toArgb()
        }
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isLightIcons
    }
}

@Composable
fun StatusBarColorChangerScreen() {
    StatusBarColorChanger(
        statusBarColor = Color.Red,
        navigationBarColor = Color.Green,
        isLightIcons = true
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello! Compose")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StatusBarColorChangerPreview() {
    StatusBarColorChangerScreen()
}