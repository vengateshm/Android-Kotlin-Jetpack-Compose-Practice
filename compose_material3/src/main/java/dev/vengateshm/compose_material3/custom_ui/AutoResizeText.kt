package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit

@Composable
fun AutoResizeText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    targetTextSize: TextUnit = style.fontSize,
    maxLines: Int = 1
) {
    var modifiedTextSize by remember {
        mutableStateOf(targetTextSize)
    }

    Text(
        modifier = modifier,
        text = text,
        fontSize = modifiedTextSize,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResult ->
            val lastLineIndex = textLayoutResult.lineCount - 1

            if (textLayoutResult.isLineEllipsized(lastLineIndex)) {
                modifiedTextSize = modifiedTextSize.times(0.7)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AutoResizeTextPreview() {
    AutoResizeText(
        text = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 10
    )
}