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
import androidx.constraintlayout.compose.Dimension

@Composable
fun ChainWeighted() {
    ConstraintLayout(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = Color.White),
    ) {
        val (box1, box2, box3) = createRefs()

        Box(
            modifier =
                Modifier
                    .size(0.dp)
                    .background(color = Color.Cyan)
                    .constrainAs(box1) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                        horizontalChainWeight = 1f
                    },
        )
        Box(
            modifier =
                Modifier
                    .size(0.dp)
                    .background(color = Color.Black)
                    .constrainAs(box2) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                        horizontalChainWeight = 2f
                    },
        )
        Box(
            modifier =
                Modifier
                    .size(0.dp)
                    .background(color = Color.Magenta)
                    .constrainAs(box3) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                        horizontalChainWeight = 1f
                    },
        )

        createHorizontalChain(
            box1,
            box2,
            box3,
            chainStyle = ChainStyle.SpreadInside,
        )
        /*createVerticalChain(
            box1, box2, box3,
            chainStyle = ChainStyle.Spread
        )*/
    }
}

@Preview
@Composable
fun ChainWeightedPreview() {
    ChainWeighted()
}
