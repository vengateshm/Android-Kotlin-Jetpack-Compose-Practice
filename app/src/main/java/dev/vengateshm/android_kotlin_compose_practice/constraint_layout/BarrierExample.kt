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
fun BarrierExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val (name, age, button) = createRefs()
        val nameBarrier = createEndBarrier(name)
        Text(text = "Full Name",
            Modifier.constrainAs(name) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            })
        Text(text = "Age",
            Modifier.constrainAs(age) {
                start.linkTo(parent.start)
                top.linkTo(name.bottom)
            })
        Button(onClick = { },
            Modifier.constrainAs(button) {
                start.linkTo(nameBarrier)
                top.linkTo(parent.top)
            }) {
            Text(text = "Click!")
        }
    }
}

@Preview
@Composable
fun BarrierExamplePreview() {
    BarrierExample()
}