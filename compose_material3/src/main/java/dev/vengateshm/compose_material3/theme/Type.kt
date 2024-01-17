package dev.vengateshm.compose_material3.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val InterRegular = FontFamily(Font(R.font.inter_regular))
val InterBold = FontFamily(Font(R.font.inter_bold))
val InterSemiBold = FontFamily(Font(R.font.inter_semibold))
val InterMedium = FontFamily(Font(R.font.inter_medium))