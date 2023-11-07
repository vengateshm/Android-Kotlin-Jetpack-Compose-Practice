package dev.vengateshm.android_kotlin_compose_practice.coil_image_caching

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import coil.memory.MemoryCache

class CoilImageCachingActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageUrl = "https://cdn.pixabay.com/photo/2023/09/14/19/14/landscape-8253576_1280.jpg"
        val imageUrl2 = "https://cdn.pixabay.com/photo/2023/09/10/15/15/flowers-8245210_1280.png"

        setContent {
            MaterialTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = imageUrl, contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1280f / 850f)
                    )
                    SubcomposeAsyncImage(
                        model = imageUrl2, contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1280f / 1280f),
                        loading = {
                            CircularProgressIndicator()
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
//                        imageLoader.diskCache?.clear()
//                        imageLoader.memoryCache?.clear()
                        // imageUrl is the key - remove only that image fro cache
                        imageLoader.diskCache?.remove(imageUrl)
                        imageLoader.memoryCache?.remove(MemoryCache.Key(imageUrl))
                    }) {
                        Text(text = "Clear Cache")
                    }
                }
            }
        }
    }
}