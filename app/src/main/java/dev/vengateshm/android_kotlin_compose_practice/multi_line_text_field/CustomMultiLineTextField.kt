package dev.vengateshm.android_kotlin_compose_practice.multi_line_text_field

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun CustomMultiLineTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String = "",
    textStyle: TextStyle = MaterialTheme.typography.body1,
    maxLines: Int = 4,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        maxLines = maxLines,
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hintText,
                        color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
                    )
                }
                innerTextField()
            }
        }
    )
}