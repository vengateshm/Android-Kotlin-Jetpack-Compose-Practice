package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.theme.Material3AppTheme

@Composable
fun ExpandableText(modifier: Modifier = Modifier) {
    val fullText =
        "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc."
    val maxLines = 1
    val readMoreText = "... more"
    val readLessText = "... less"

    var isTextExceedingMaxLines by remember { mutableStateOf(false) }
    var lastVisibleLineIndex by remember { mutableStateOf(0) }
    var isExpanded by remember { mutableStateOf(false) }

    val adjustedText = if (isTextExceedingMaxLines) fullText.substring(
        0,
        lastVisibleLineIndex - readMoreText.length
    ) else fullText

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 16.sp)) {
            append(if (isExpanded) fullText else adjustedText)
            val text = if (isExpanded) readLessText else readMoreText
            val tag = if (isExpanded) "read_less" else "read_more"
            val color = if (isExpanded) Color(0xFFA41818) else Color(0xFF114B11)
            val linkAnnotation = LinkAnnotation.Clickable(
                tag = tag,
                style = SpanStyle(color = color, fontSize = 14.sp),
                linkInteractionListener = {
                    isExpanded = !isExpanded
                })
            withLink(link = linkAnnotation) {
                append(text)
            }
        }
    }

    BasicText(
        text = annotatedString,
        maxLines = if (!isExpanded) 1 else Int.MAX_VALUE,
        onTextLayout = { textLayoutResult ->
            isTextExceedingMaxLines = textLayoutResult.layoutInput.softWrap
            lastVisibleLineIndex = textLayoutResult.getLineEnd(maxLines - 1)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ExpandableTextPreview() {
    Material3AppTheme {
        ExpandableText()
    }
}