package dev.vengateshm.compose_material3.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GooglePhotosLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(300.dp)) {
        drawArc(
            color = Color(0XFF4B7CDC),
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(width = size.width.times(0.5f), height = size.height.times(0.5f)),
            topLeft = Offset(x=size.width.times(0.5f),y=size.height.times(0.25f))
        )
        drawArc(
            color = Color(0XFFF2BE42),
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(width = size.width.times(0.5f), height = size.height.times(0.5f)),
            topLeft = Offset(x=0f,y=size.height.times(0.25f))
        )
        drawArc(
            color = Color(0XFFD9003F),
            startAngle = -90f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(width = size.width.times(0.5f), height = size.height.times(0.5f)),
            topLeft = Offset(x=size.width.times(0.25f),y=0f)
        )
        drawArc(
            color = Color(0XFF58A55C),
            startAngle = -90f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(width = size.width.times(0.5f), height = size.height.times(0.5f)),
            topLeft = Offset(x=size.width.times(0.25f),y=size.height.times(0.5f))
        )
    }
}

@Preview
@Composable
private fun GooglePhotosLogoPreview() {
    GooglePhotosLogo()
}