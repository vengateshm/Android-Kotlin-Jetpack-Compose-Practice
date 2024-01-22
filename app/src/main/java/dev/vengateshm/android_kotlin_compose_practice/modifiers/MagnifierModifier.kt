package dev.vengateshm.android_kotlin_compose_practice.modifiers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.vengateshm.android_kotlin_compose_practice.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MagnifierModifier() {
    var offset =
        remember {
            Offset.Zero
        }

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .pointerInput(true) {
                    detectDragGestures { change, _ ->
                        offset = change.position
                    }
                }
//        .magnifier(
//            sourceCenter = { offset },
//            magnifierCenter = { Offset(x = 0f, y = 200f) },
//            style = MagnifierStyle(
//                size = DpSize(100.dp, 100.dp),
//                cornerRadius = 100.dp
//            )
//        )
                .drawWithContent {
                    drawContent()
                    drawCircle(
                        color = Color.DarkGray,
                        radius = 200f,
                        center = offset,
                    )
                },
    ) {
        Image(
            painter = painterResource(id = R.drawable.music),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Preview
@Composable
fun MagnifierModifierPreview() {
    MagnifierModifier()
}
