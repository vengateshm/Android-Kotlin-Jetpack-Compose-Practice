package dev.vengateshm.compose_material3.canvas

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.theme.Material3AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DrawCircleOnCanvasTap(modifier: Modifier = Modifier) {

    var lastTapOffset by remember { mutableStateOf(Offset(x = 100f, y = 100f)) }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Canvas(modifier = Modifier
                .fillMaxSize()
                .pointerInteropFilter { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_MOVE -> {
                            lastTapOffset = Offset(x = motionEvent.x, y = motionEvent.y)
                        }
                    }
                    true
                }) {
                drawCircle(
                    color = Color.Black,
                    radius = 20.dp.toPx(),
                    center = lastTapOffset
                )
            }
        }
    }

}

@Preview
@Composable
private fun DrawCircleOnCanvasTapPreview() {
    Material3AppTheme {
        DrawCircleOnCanvasTap()
    }
}