package dev.vengateshm.android_kotlin_compose_practice.custom_composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.isUnspecified

@Composable
fun AutoResizeText(
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    modifier: Modifier = Modifier,
    color: Color = textStyle.color,
) {
    var resizedTextStyle by remember {
        mutableStateOf(textStyle)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }
    val defaultFontSize = MaterialTheme.typography.body1.fontSize

    Text(
        modifier = modifier.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        text = text,
        color = color,
        softWrap = false,
        style = resizedTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                if (textStyle.fontSize.isUnspecified) {
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95
                )
            } else {
                shouldDraw = true
            }
        })
}