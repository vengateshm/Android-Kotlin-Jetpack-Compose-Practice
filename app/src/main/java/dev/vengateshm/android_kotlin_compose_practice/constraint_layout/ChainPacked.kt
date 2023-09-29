package dev.vengateshm.android_kotlin_compose_practice.constraint_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ChainPacked() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (box1, box2, box3) = createRefs()

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Red)
                .constrainAs(box1) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Green)
                .constrainAs(box2) {
                    start.linkTo(box1.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Blue)
                .constrainAs(box3) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        /*createHorizontalChain(
            box1, box2, box3,
            chainStyle = ChainStyle.Packed,
        )
        createHorizontalChain(
            box1, box2, box3,
            chainStyle = ChainStyle.Spread,
        )*/
        createHorizontalChain(
            box1, box2, box3,
            chainStyle = ChainStyle.SpreadInside,
        )
    }
}

@Preview
@Composable
fun ChainPackedPreview() {
    ChainPacked()
}