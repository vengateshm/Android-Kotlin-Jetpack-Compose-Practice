package dev.vengateshm.compose_material3.image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder

@Composable
fun ServerImage(modifier: Modifier = Modifier) {
  val context = LocalContext.current
  Column(modifier = Modifier.fillMaxSize()) {
    AsyncImage(
      model = ImageRequest.Builder(context)
        .data("https://dummyimage.com/600x400/000/fff")
        .build(),
      contentDescription = null,
      modifier = modifier.size(150.dp),
    )
    AsyncImage(
      model = ImageRequest.Builder(context)
        .data("https://www.svgrepo.com/show/19461/url-link.svg")
        .decoderFactory(SvgDecoder.Factory())
        .build(),
      contentDescription = null,
      modifier = modifier.size(150.dp),
    )
  }
}