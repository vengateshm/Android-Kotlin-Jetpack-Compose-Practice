package dev.vengateshm.compose_material3.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GraphicsLayerSample(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            modifier = Modifier.graphicsLayer {
                alpha = 0.3f
            },
            onClick = { }) {
            Text(text = "Alpha")
        }
        Button(
            modifier = Modifier.graphicsLayer {
                renderEffect = BlurEffect(radiusX = 3f, radiusY = 3f)
            },
            onClick = { }) {
            Text(text = "Blur")
        }
        Button(
            modifier = Modifier
                .graphicsLayer {
                    shape = CutCornerShape(20.dp)
                    clip = true
                },
            onClick = {}) {
            Text(text = "Shape", color = MaterialTheme.colorScheme.onPrimary)
        }
        Button(
            modifier = Modifier.graphicsLayer {
                rotationX = 45f
            },
            onClick = { }) {
            Text(text = "Rotation X")
        }
        Button(
            modifier = Modifier.graphicsLayer {
                rotationY = 45f
            },
            onClick = { }) {
            Text(text = "Rotation Y")
        }
        Button(
            modifier = Modifier.graphicsLayer {
                rotationZ = 45f
            },
            onClick = { }) {
            Text(text = "Rotation Z")
        }
        Button(
            modifier = Modifier.graphicsLayer {
                scaleX = 1.5f
            },
            onClick = { }) {
            Text(text = "Scale X")
        }
        Button(
            modifier = Modifier.graphicsLayer {
                scaleY = 1.5f
            },
            onClick = { }) {
            Text(text = "Scale Y")
        }
        Button(
            modifier = Modifier.graphicsLayer {
                translationX = 50f
            },
            onClick = { }) {
            Text(text = "Translation X")
        }
        Button(
            modifier = Modifier.graphicsLayer {
                translationY = 20f
            },
            onClick = { }) {
            Text(text = "Translation Y")
        }
        Button(
            modifier = Modifier.graphicsLayer {
                shadowElevation = 10f
                shape = CircleShape
                clip = true
            },
            onClick = { }) {
            Text(text = "Shadow Elevation")
        }
        Button(
            modifier = Modifier.graphicsLayer {
                rotationY = 60f
                cameraDistance = 20f
            },
            onClick = { }) {
            Text(text = "Camera Distance ")
        }
    }
}

@Preview
@Composable
private fun GraphicsLayerSamplePreview() {
    GraphicsLayerSample()
}