package dev.vengateshm.android_kotlin_compose_practice.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val RedLight = Color(0XFFFF4F5B)
val Grey300 = Color(0XFFe0e0e0)
val Grey900 = Color(0XFF212121)

object ColorSets {
    val set1 = ColorSet(
        primary = Color(0xFFFF5722),         // Orange
        primaryVariant = Color(0xFFE64A19), // Dark Orange
        secondary = Color(0xFF03A9F4)      // Light Blue
    )

    val set2 = ColorSet(
        primary = Color(0xFF2196F3),         // Blue
        primaryVariant = Color(0xFF1976D2), // Dark Blue
        secondary = Color(0xFFFF4081)      // Pink
    )

    val set3 = ColorSet(
        primary = Color(0xFF4CAF50),         // Green
        primaryVariant = Color(0xFF388E3C), // Dark Green
        secondary = Color(0xFFFF5722)      // Orange
    )

    val set4 = ColorSet(
        primary = Color(0xFF607D8B),         // Gray
        primaryVariant = Color(0xFF455A64), // Dark Gray
        secondary = Color(0xFFFF9800)      // Amber
    )

    val set5 = ColorSet(
        primary = Color(0xFFFFC107),         // Yellow
        primaryVariant = Color(0xFFFFA000), // Dark Yellow
        secondary = Color(0xFFFF5722)      // Orange
    )

    val set6 = ColorSet(
        primary = Color(0xFFFFFFFF),         // White
        primaryVariant = Color(0xFFF5F5F5), // Light Gray
        secondary = Color(0xFF212121)      // Black
    )

    val set7 = ColorSet(
        primary = Color(0xFFFF5722),         // Orange
        primaryVariant = Color(0xFFE64A19), // Dark Orange
        secondary = Color(0xFFFFD600)      // Yellow
    )

    val set8 = ColorSet(
        primary = Color(0xFF8BC34A),         // Lime Green
        primaryVariant = Color(0xFF689F38), // Dark Green
        secondary = Color(0xFF009688)      // Teal
    )

    val set9 = ColorSet(
        primary = Color(0xFF9C27B0),         // Purple
        primaryVariant = Color(0xFF7B1FA2), // Dark Purple
        secondary = Color(0xFFFF9800)      // Amber
    )

    val set10 = ColorSet(
        primary = Color(0xFF795548),         // Brown
        primaryVariant = Color(0xFF5D4037), // Dark Brown
        secondary = Color(0xFF9E9E9E)      // Grey
    )

    val set11 = ColorSet(
        primary = Color(0xFF673AB7),         // Deep Purple
        primaryVariant = Color(0xFF512DA8), // Dark Deep Purple
        secondary = Color(0xFF607D8B)      // Blue Grey
    )

    val set12 = ColorSet(
        primary = Color(0xFF00BCD4),         // Cyan
        primaryVariant = Color(0xFF0097A7), // Dark Cyan
        secondary = Color(0xFFFF5722)      // Orange
    )
}

data class ColorSet(
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
)