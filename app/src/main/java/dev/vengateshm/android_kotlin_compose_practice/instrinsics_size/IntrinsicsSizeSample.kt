package dev.vengateshm.android_kotlin_compose_practice.instrinsics_size

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TwoTexts(
    modifier: Modifier = Modifier,
    text1: String,
    text2: String,
) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier =
                Modifier
                    .padding(start = 4.dp)
                    .wrapContentWidth(Alignment.Start),
            text = text1,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Divider(
            color = Color.Black,
            modifier =
                Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxHeight()
                    .width(1.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier =
                Modifier
                    .padding(end = 4.dp)
                    .wrapContentWidth(Alignment.End),
            text = text2,
        )
    }
}

@Preview
@Composable
fun TwoTextsPreview() {
    MaterialTheme {
        Surface {
            TwoTexts(text1 = "Hi\nHi\nHi", text2 = "there")
        }
    }
}
