package dev.vengateshm.android_kotlin_compose_practice.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette =
    darkColors(
        primary = ColorSets.set9.primary,
        primaryVariant = ColorSets.set9.primaryVariant,
        secondary = ColorSets.set9.secondary,
    )

private val LightColorPalette =
    lightColors(
        primary = ColorSets.set9.primary,
        primaryVariant = ColorSets.set9.primaryVariant,
        secondary = ColorSets.set9.secondary,
        /* Other default colors to override
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
         */
    )

@Composable
fun AndroidKotlinComposePracticeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content:
        @Composable()
        () -> Unit,
) {
    val colors =
        if (darkTheme) {
            DarkColorPalette
        } else {
            LightColorPalette
        }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}

private val WalkthroughScreenDarkColorPalette =
    darkColors(
        primary = RedLight,
        primaryVariant = Grey900,
        secondary = Teal200,
    )

private val WalkthroughScreenLightColorPalette =
    lightColors(
        primary = RedLight,
        primaryVariant = Grey900,
        secondary = Teal200,
        onBackground = Grey900,
    )

@Composable
fun OnBoardingScreenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors =
        if (darkTheme) {
            WalkthroughScreenDarkColorPalette
        } else {
            WalkthroughScreenLightColorPalette
        }

    MaterialTheme(
        colors = colors,
        typography = WalkthroughScreenTypography,
        shapes = Shapes,
        content = content,
    )
}

@Composable
fun ProductSansFontTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors =
        if (darkTheme) {
            DarkColorPalette
        } else {
            LightColorPalette
        }

    MaterialTheme(
        colors = colors,
        typography = ProductSansFontThemeTypography,
        shapes = Shapes,
        content = content,
    )
}
