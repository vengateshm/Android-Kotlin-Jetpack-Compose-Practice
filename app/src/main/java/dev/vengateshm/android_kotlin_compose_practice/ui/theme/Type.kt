package dev.vengateshm.android_kotlin_compose_practice.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.vengateshm.android_kotlin_compose_practice.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val quickSand = FontFamily(
    Font(R.font.quicksand_bold, FontWeight.Bold),
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold)
)

val WalkthroughScreenTypography = Typography(
    body1 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp
    )
)

val productSans = FontFamily(
    Font(R.font.product_sans_bold, FontWeight.Bold),
    Font(R.font.product_sans_light, FontWeight.Light),
    Font(R.font.product_sans_medium, FontWeight.Medium),
    Font(R.font.product_sans_regular, FontWeight.Normal),
    Font(R.font.product_sans_bold, FontWeight.SemiBold)
)

val ProductSansFontThemeTypography = Typography(
    body1 = TextStyle(
        fontFamily = productSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)