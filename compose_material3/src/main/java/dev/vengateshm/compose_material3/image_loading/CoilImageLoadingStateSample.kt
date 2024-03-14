package dev.vengateshm.compose_material3.image_loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun CoilImageLoadingStateSample() {
    val context = LocalContext.current
    val imageUrl = remember {
        "https://cdn.pixabay.com/photo/2023/09/14/19/14/landscape-8253576_1280.jpg"
    }
    val model = remember {
        ImageRequest
            .Builder(context)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build()
    }
    val aynscImageState = rememberAsyncImagePainter(model = model).state
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (aynscImageState) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }

            is AsyncImagePainter.State.Error -> {
                Text(text = aynscImageState.result.throwable.localizedMessage)
            }

            is AsyncImagePainter.State.Empty -> {
                Text(text = aynscImageState.toString())
            }

            is AsyncImagePainter.State.Success -> {
                Image(painter = aynscImageState.painter, contentDescription = "")
            }
        }
    }
}