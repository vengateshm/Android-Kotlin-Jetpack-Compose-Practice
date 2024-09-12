package dev.vengateshm.compose_material3.ui_concepts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRow1(modifier: Modifier = Modifier) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = 2,
    ) {
        repeat(6) {
            if (it % 3 == 0) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .border(2.dp, Color.Green),
                )
            } else {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(100.dp)
                        .background(Color.Green),
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRow2(modifier: Modifier = Modifier) {
    FlowRow(
        maxItemsInEachRow = 3,
    ) {
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(100.dp)
                .background(Color.Blue),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)// 70% of screen width
                .height(100.dp)
                .background(Color.Gray),
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(100.dp)
                .background(Color.Green),
        )
    }
}

@Composable
fun Row2(modifier: Modifier = Modifier) {
    Row {
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(100.dp)
                .background(Color.Blue),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)// 70% of remaining width
                .height(100.dp)
                .background(Color.Gray),
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(100.dp)
                .background(Color.Green),
        )
    }
}

@Preview
@Composable
private fun FlowRow1Preview() {
    FlowRow1()
}

@Preview
@Composable
private fun FlowRow2Preview() {
    FlowRow2()
}

@Preview
@Composable
private fun Row2Preview() {
    Row2()
}