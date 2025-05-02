package dev.vengateshm.commonui.compose.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val UiColorScheme = lightColorScheme(
    primary = Black,
    secondary = UiBlue,
    tertiary = DarkGrey,
    background = MegaSuperUltraDarkGrey,
    onBackground = VengeanceGrey,
    surface = MegaSuperUltraDarkGrey,
    onSurface = VengeanceGrey,
)

@Composable
fun CommonUiAppTheme(
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        // isInEditMode is a property of View. It returns true if the view is being rendered in an IDE preview (like Android Studio's Preview or Layout Editor).
        SideEffect {
            (view.context as? Activity)?.window?.let { window ->
                window.statusBarColor = UiColorScheme.primary.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = UiColorScheme,
        typography = Typography,
        content = content,
    )
}