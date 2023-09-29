package dev.vengateshm.android_kotlin_compose_practice.constraint_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun GuidelineExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val gTop = createGuidelineFromTop(fraction = 0.5f)
        val button = createRef()

        Button(
            modifier = Modifier.constrainAs(button) {
                start.linkTo(parent.start)
                top.linkTo(gTop)
                end.linkTo(parent.end)
            },
            onClick = { }) {
            Text(text = "I am at 50% of the screen height")
        }
    }
}

@Preview
@Composable
fun GuidelineExamplePreview() {
    GuidelineExample()
}