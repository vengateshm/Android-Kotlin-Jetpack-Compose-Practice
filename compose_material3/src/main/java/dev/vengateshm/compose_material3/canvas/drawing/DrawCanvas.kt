package dev.vengateshm.compose_material3.canvas.drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.abs

@Composable
fun DrawCanvasRoot(modifier: Modifier = Modifier) {
    val viewModel = viewModel<DrawStateViewModel>()
    val state: DrawState by viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DrawCanvas(
            paths = state.paths,
            currentPath = state.currentPath,
            onDrawAction = viewModel::onDrawAction,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
        CanvasControls(
            selectedColor = state.selectedColor,
            colors = allColors,
            onColorSelected = { color ->
                viewModel.onDrawAction(DrawAction.OnSelectColor(color))
            },
            onClearCanvas = {
                viewModel.onDrawAction(DrawAction.OnClear)
            },
        )
    }
}

@Composable
fun DrawCanvas(
    paths: List<PathData>,
    currentPath: PathData?,
    onDrawAction: (DrawAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier
            .clipToBounds()
            .background(Color.White)
            .pointerInput(true) {
                detectDragGestures(
                    onDragStart = {
                        onDrawAction(DrawAction.OnPathStart)
                    },
                    onDrag = { change, _ ->
                        onDrawAction(DrawAction.OnPathMove(change.position))
                    },
                    onDragEnd = {
                        onDrawAction(DrawAction.OnPathEnd)
                    },
                    onDragCancel = {
                        onDrawAction(DrawAction.OnPathEnd)
                    },
                )
            },
    ) {
        paths.forEach { pathData: PathData ->
            drawPath(
                paths = pathData.path,
                color = pathData.color,
            )
        }
        currentPath?.let { pathData ->
            drawPath(
                paths = pathData.path,
                color = pathData.color,
            )
        }
    }
}

@Composable
fun ColumnScope.CanvasControls(
    selectedColor: Color,
    colors: List<Color>,
    onColorSelected: (Color) -> Unit,
    onClearCanvas: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
    ) {
        colors.forEach { color ->
            val isSelected = selectedColor == color
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        val scale = if (isSelected) 1.2f else 1f
                        scaleX = scale
                        scaleY = scale
                    }
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color)
                    .border(
                        width = 2.dp,
                        color = if (isSelected) Color.Black else Color.Transparent,
                        shape = CircleShape,
                    )
                    .clickable {
                        onColorSelected(color)
                    },
            )
        }
    }
    Button(
        onClick = {
            onClearCanvas()
        },
    ) {
        Text(text = "Clear Canvas")
    }
}

private fun DrawScope.drawPath(
    paths: List<Offset>,
    color: Color,
    thickness: Float = 10f,
) {
    val smoothedPath = Path().apply {
        if (paths.isNotEmpty()) {
            moveTo(paths.first().x, paths.first().y)

            val smoothnessFactor = 5
            for (i in 1..paths.lastIndex) {
                val from = paths[i - 1]
                val to = paths[i]
                val dx = abs(from.x - to.x)
                val dy = abs(from.y - to.y)
                if (dx > smoothnessFactor || dy > smoothnessFactor) {
                    quadraticTo(
                        x1 = (from.x + to.x) / 2,
                        y1 = (from.y + to.y) / 2,
                        x2 = to.x,
                        y2 = to.y,
                    )
                }
            }
        }
    }
    drawPath(
        path = smoothedPath,
        color = color,
        style = Stroke(
            width = thickness,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round,
        ),
    )
}

@Preview
@Composable
private fun DrawCanvasPreview() {
    DrawCanvasRoot()
}