package dev.vengateshm.commonui.compose.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import dev.vengateshm.commonui.R

private val InterFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.ExtraLight),
    Font(R.font.inter_regular, FontWeight.Light),
    Font(R.font.inter_regular, FontWeight.Thin),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_medium, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_bold, FontWeight.ExtraBold),
)

private val DefaultLineHeightStyle = LineHeightStyle(
    alignment = LineHeightStyle.Alignment.Center,
    trim = LineHeightStyle.Trim.None,
)

private val InterTextStyle = TextStyle(
    fontFamily = InterFamily,
    lineHeightStyle = DefaultLineHeightStyle,
)

private val InterSpacedTextStyle = InterTextStyle.copy(
    letterSpacing = 2.sp,
)

val CommonUiButtonStyle = InterSpacedTextStyle.copy(
    fontWeight = FontWeight.SemiBold,
    fontSize = CommonUiTextButton,
)

val CommonUiSectionHeaderStyle = InterTextStyle.copy(
    fontWeight = FontWeight.SemiBold,
    fontSize = CommonUiTextLarge,
)

val CommonUiBodyStyle = InterTextStyle.copy(
    fontWeight = FontWeight.Normal,
    fontSize = CommonUiTextMedium,
)

val CommonUiTitleStyle = InterTextStyle.copy(
    fontWeight = FontWeight.Medium,
    fontSize = CommonUiTitleSize,
)

val CommonUiCaptionStyle = InterTextStyle.copy(
    fontWeight = FontWeight.Normal,
    fontSize = CommonUiTextSmall,
)

val Typography = Typography(
    headlineLarge = CommonUiSectionHeaderStyle,
    headlineMedium = CommonUiSectionHeaderStyle,
    headlineSmall = CommonUiSectionHeaderStyle,
    titleLarge = CommonUiSectionHeaderStyle,
    titleMedium = CommonUiSectionHeaderStyle,
    bodyLarge = CommonUiBodyStyle,
    bodyMedium = CommonUiBodyStyle,
    bodySmall = CommonUiCaptionStyle,
    labelSmall = CommonUiCaptionStyle,
)