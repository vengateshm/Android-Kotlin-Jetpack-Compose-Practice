package dev.vengateshm.compose_material3.api_compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DiagonalLayoutSample(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        DiagonalLayout {
            Text(text = "Box count : ${childCount - 1}", fontSize = 24.sp)
            repeat(3) {
                Box(
                    modifier = Modifier.size(80.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0XFFFFB463),
                                    Color(0XFFFE6197)
                                )
                            )
                        )
                        .selected(it == 1)
                )
            }
        }
    }
}

@Composable
fun DiagonalLayout(
    modifier: Modifier = Modifier,
    content: @Composable DiagonalScope.() -> Unit,
) {
    var childCount by remember { mutableIntStateOf(0) }
    val diagonalContent = @Composable { DiagonalScope(childCount).content() }

    Layout(
        modifier = modifier,
        content = diagonalContent
    ) { measurables, constraints ->

        childCount = measurables.size

        val placeables =
            measurables.map { it.measure(constraints.copy(minWidth = 0, minHeight = 0)) }
        var x = 0
        var y = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach {

                val selected = (it.parentData as DiagonalChildData?)?.select ?: false

                //it.place(x = x, y = y)
                it.placeRelativeWithLayer(x = x, y = y) {
                    rotationZ = if (selected) 45f else 0f
                }
                x += it.width
                y += it.height
            }
        }
    }
}

@LayoutScopeMarker
data class DiagonalScope(val childCount: Int) {
    fun Modifier.selected(select: Boolean) = then(DiagonalChildData(select = select))
}

data class DiagonalChildData(val select: Boolean = false) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any = this@DiagonalChildData
}