package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ClickableSpanSample(modifier: Modifier = Modifier) {
  val fullText = "By proceeding, you agree to our Terms of Service and Privacy Policy."
  val terms = "Terms of Service"
  val privacy = "Privacy Policy"
  var lastClicked by remember { mutableStateOf("--") }

  Column {
    Text(text = "Last clicked: $lastClicked")
    ClickableText(
      fullText = fullText,
      clickables = listOf(
        ClickableItem(terms, Color.Red, onClick = { lastClicked = terms }),
        ClickableItem(privacy, Color.Blue, onClick = { lastClicked = privacy }),
      ),
    )
  }
}

@Composable
fun ClickableText(
  fullText: String,
  modifier: Modifier = Modifier,
  clickables: List<ClickableItem> = emptyList(),
) {
  val text = createClickableText(
    fullText = fullText,
    clickableItems = clickables.toTypedArray(),
  )
  Text(modifier = modifier, text = text)
}

@Immutable
data class ClickableItem(
  val clickableText: String,
  val color: Color = Color.Unspecified,
  val onClick: () -> Unit,
)

fun createClickableText(
  fullText: String,
  vararg clickableItems: ClickableItem,
) = buildAnnotatedString {
  append(fullText)
  clickableItems.forEach { clickableItem ->
    if (fullText.contains(clickableItem.clickableText)) {
      addLink(
        clickable = LinkAnnotation.Clickable(
          tag = clickableItem.clickableText,
          styles = TextLinkStyles(style = SpanStyle(color = clickableItem.color)),
          linkInteractionListener = {
            clickableItem.onClick()
          },
        ),
        start = fullText.indexOf(clickableItem.clickableText),
        end = fullText.indexOf(clickableItem.clickableText) + clickableItem.clickableText.length,
      )
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ClickableSpanSamplePreview() {
  ClickableSpanSample()
}