package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun TextWithClickableLinks(modifier: Modifier = Modifier) {
    val fullText =
        "Hi! I am Vengatesh. I work as a Senior Software Developer. I have 8 years of expertise in developing native android applications."
    val linkText = "https://github.com/vengateshm"

    val uriHandler = LocalUriHandler.current

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 16.sp)) {
            append(fullText)
            append("\n")
            withLink(
                link = LinkAnnotation.Url(
                    url = linkText,
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = Color(
                                0xFF0E72C2
                            ), textDecoration = TextDecoration.Underline
                        )
                    ),
                    linkInteractionListener = {
                        uriHandler.openUri(linkText)
                    }
                )
            ) {
                append(linkText)
            }
        }
    }

    BasicText(text = annotatedString)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TextWithClickableLinksPreview() {
    TextWithClickableLinks()
}