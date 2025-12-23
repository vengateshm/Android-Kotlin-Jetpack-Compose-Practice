package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun RetainSample(modifier: Modifier = Modifier) {
  MoviePlayer(url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4")
}

@Composable
fun MoviePlayer(
  url: String,
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current
  val applicationContext = context.applicationContext

  val exoplayer = /*remember {
    ExoPlayer.Builder(applicationContext)
      .build()
      .apply {
        setMediaItem(MediaItem.fromUri(url))
        prepare()
        playWhenReady = true
      }
  }*/

    retain {
      ExoPlayer.Builder(applicationContext)
        .build()
        .apply {
          setMediaItem(MediaItem.fromUri(url))
          prepare()
          playWhenReady = true
        }
    }

  val lifecycleOwner = LocalLifecycleOwner.current
  DisposableEffect(lifecycleOwner) {
    val observer = LifecycleEventObserver { _, event ->
      if (event == Lifecycle.Event.ON_PAUSE) {
        exoplayer.pause()
      } else if (event == Lifecycle.Event.ON_RESUME) {
        exoplayer.play()
      }
    }

    lifecycleOwner.lifecycle.addObserver(observer)

    onDispose {
      lifecycleOwner.lifecycle.removeObserver(observer)
    }
  }

  AndroidView(
    modifier = Modifier
      .padding(16.dp)
      .aspectRatio(16f / 9f)
      .clip(RoundedCornerShape(16.dp)),
    factory = { ctx ->
      PlayerView(ctx)
        .apply { player = exoplayer }
    },
  )
}