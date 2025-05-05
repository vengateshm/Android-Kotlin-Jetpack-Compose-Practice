package dev.vengateshm.compose_material3.custom_colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val surface: Color,
    val surfaceLight: Color,
    val textPrimary: Color,
    val textInverse: Color,
    val iconPrimary: Color,
    val iconInverse: Color,
    val borderPrimary: Color,
    val borderError: Color,
    val borderFocused: Color,
    val buttonPrimary: Color,
    val buttonDisabled: Color,
    val isLight: Boolean,
)

val lightThemeColors = CustomColors(
    surface = Color(0xFFFFFFFF),
    surfaceLight = Color(0xFFFAFAFA),
    textPrimary = Color.Black,
    textInverse = Color.White,
    iconPrimary = Color.Black,
    iconInverse = Color.White,
    borderPrimary = Color(0xFFBEBEBE),
    borderError = Color(0xFFC53638),
    borderFocused = Color.Black,
    buttonPrimary = Color(0xFFDC6B0F),
    buttonDisabled = Color(0xFFDDDDDD),
    isLight = true,
)

val darkThemeColors = CustomColors(
    surface = Color(0xFF000000),
    surfaceLight = Color(0xFF232323),
    textPrimary = Color.White,
    textInverse = Color.Black,
    iconPrimary = Color.White,
    iconInverse = Color.Black,
    borderPrimary = Color(0xFF4D4D4D),
    borderError = Color(0xFFFF6E70),
    borderFocused = Color.White,
    buttonPrimary = Color(0xFFFFAE6C),
    buttonDisabled = Color(0xFF454545),
    isLight = false,
)

val LocalCustomColors = staticCompositionLocalOf<CustomColors> {
    error("No custom colors provided")
}