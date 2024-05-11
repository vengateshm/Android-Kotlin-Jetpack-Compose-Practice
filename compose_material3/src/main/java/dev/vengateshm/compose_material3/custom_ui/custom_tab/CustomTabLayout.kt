package dev.vengateshm.compose_material3.custom_ui.custom_tab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.theme.Material3AppTheme

@Composable
fun CustomTabLayoutSample(modifier: Modifier = Modifier) {
    val pages = listOf("Personal Details", "Technical Details", "Other Details")
    var screen by remember {
        mutableIntStateOf(0)
    }
    CustomTabLayout(
        pages = pages,
        screen = screen,
        onClick = {
            screen = it
        }
    )
}

@Composable
fun CustomTabLayout(
    modifier: Modifier = Modifier, pages: List<String>, screen: Int,
    onClick: (Int) -> Unit
) {

    val color = MaterialTheme.colorScheme.onBackground

    var startOffset by remember {
        mutableStateOf(Pair(0f, 0f))
    }
    var endOffset by remember {
        mutableStateOf(Pair(0f, 0f))
    }

    Canvas(modifier = Modifier.fillMaxWidth()) {
        drawLine(
            color = color,
            strokeWidth = 4f,
            end = Offset(x = endOffset.first, y = endOffset.second),
            start = Offset(x = startOffset.first, y = startOffset.second),
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        pages.forEachIndexed { index, page ->
            Column(
                modifier = Modifier.clickable {
                    onClick(index)
                },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderBox(
                    title = page,
                    active = screen == index,
                    visible = screen >= index
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .onGloballyPositioned {
                            when (index) {
                                0 -> {
                                    startOffset = it.getCenter()
                                }

                                pages.lastIndex -> {
                                    endOffset = it.getCenter()
                                }
                            }
                        }
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        drawCircle(
                            color = color,
                            radius = if (screen == index) 12f else 8f
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HeaderBox(title: String, active: Boolean, visible: Boolean) {
    Box(
        modifier = Modifier.height(24.dp)
    ) {
        AnimatedVisibility(visible = visible) {
            Text(
                text = title,
                fontWeight = if (active) FontWeight.Bold else FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


fun LayoutCoordinates.getCenter(): Pair<Float, Float> {
    val bounds = this.boundsInRoot()
    val radius = (bounds.right - bounds.left) / 2
    val yCenter = bounds.top + radius
    val xCenter = bounds.left + radius
    println("xCenter:$xCenter")
    println("yCenter:$yCenter")
    return Pair(xCenter, yCenter)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomTabLayoutPreview() {
    Material3AppTheme {
        CustomTabLayoutSample()
    }
}
