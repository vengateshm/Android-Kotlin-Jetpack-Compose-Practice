package dev.vengateshm.android_kotlin_compose_practice.image_loader

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap

class ImageLoaderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ImageLoadingSample()
            }
        }
    }
}

@Composable
fun ImageLoadingSample() {
    var isLoading by remember { mutableStateOf(true) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    if (isLoading && bitmap == null) {
        ImageLoader.loadImage("https://cdn.pixabay.com/photo/2023/09/14/19/14/landscape-8253576_1280.jpg",
            onSuccess = {
                isLoading = false
                bitmap = it
            },
            onFailure = {
                isLoading = false
            })
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (bitmap == null && isLoading) {
            CircularProgressIndicator()
        } else if (!isLoading && bitmap != null) {
            Image(bitmap = bitmap!!.asImageBitmap(), contentDescription = null)
        } else {
            Text(text = "Error loading image")
        }
    }
}