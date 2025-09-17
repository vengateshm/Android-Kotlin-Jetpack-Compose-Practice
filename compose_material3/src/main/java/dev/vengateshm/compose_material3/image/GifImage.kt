package dev.vengateshm.compose_material3.image

import android.os.Build
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.decode.BitmapFactoryDecoder
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import kotlinx.coroutines.delay

@Composable
fun GifImage(
  modifier: Modifier = Modifier,
  url: String,
) {
  val context = LocalContext.current
  var stopAnimation by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    delay(5000)
    stopAnimation = true
  }

  if (stopAnimation) {
    AsyncImage(
      model = url,
      contentDescription = null,
      imageLoader = ImageLoader.Builder(context)
        .components {
          add(BitmapFactoryDecoder.Factory())
        }
        .build(),
      modifier = modifier,
    )
  } else {
    AsyncImage(
      model = url,
      contentDescription = null,
      imageLoader = ImageLoader.Builder(context)
        .components {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            add(AnimatedImageDecoder.Factory())
          } else {
            add(GifDecoder.Factory())
          }
        }
        .build(),
      modifier = modifier,
    )
  }
}

@Preview
@Composable
fun GifImagePreview() {
  GifImage(
    modifier = Modifier.size(250.dp),
    url = "file:///android_asset/landingpage.gif",
  )
}