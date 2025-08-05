package dev.vengateshm.commonui.compose.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import dev.vengateshm.commonui.compose.theme.CommonUiBodyStyle

@Composable
fun BodyText(
  text: String,
  modifier: Modifier = Modifier,
  style: TextStyle = CommonUiBodyStyle,
  color: Color = Color.Unspecified,
  textAlign: TextAlign? = null,
  textDecoration: TextDecoration? = null,
  fontWeight: FontWeight? = null,
  maxLines: Int = Int.MAX_VALUE,
  minLines: Int = 1,
  overflow: TextOverflow = TextOverflow.Clip,
  fontSize: TextUnit = TextUnit.Unspecified,
  lineHeight: TextUnit = TextUnit.Unspecified,
) {
  Text(
    text = text,
    style = style,
    modifier = modifier,
    color = color,
    textAlign = textAlign,
    textDecoration = textDecoration,
    fontWeight = fontWeight,
    maxLines = maxLines,
    minLines = minLines,
    overflow = overflow,
    fontSize = fontSize,
    lineHeight = lineHeight,
  )
}
