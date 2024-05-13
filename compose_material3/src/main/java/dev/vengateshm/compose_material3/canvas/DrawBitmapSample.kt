package dev.vengateshm.compose_material3.canvas

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.vengateshm.compose_material3.R

@Composable
fun DrawBitmapSample(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val imageBitmap = remember {
        mutableStateOf<ImageBitmap?>(null)
    }

    LaunchedEffect(key1 = Unit) {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.astrology)
        imageBitmap.value = Bitmap.createScaledBitmap(bitmap, 200, 200, true).asImageBitmap()
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        if (imageBitmap.value != null) {
            val width = size.width
            val height = size.height

            drawLine(
                color = Color.Black,
                start = Offset(x = 0f, y = height / 2),
                end = Offset(x = width, y = height / 2)
            )
            drawLine(
                color = Color.Black,
                start = Offset(x = width / 2, y = 0f),
                end = Offset(x = width / 2, y = height)
            )
            drawImage(
                image = imageBitmap.value!!,
                topLeft = Offset(
                    x = center.x - imageBitmap.value!!.width / 2,
                    y = center.y - imageBitmap.value!!.height / 2
                )
            )
        }
    }
}

@Preview
@Composable
private fun DrawBitmapSamplePreview() {
    DrawBitmapSample()
}