package dev.vengateshm.compose_material3.image

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import dev.vengateshm.compose_material3.R

@Composable
fun SvgImagesSample(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .safeContentPadding()
      .safeDrawingPadding()
      .safeGesturesPadding(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    val imageUrl = "http://www.svgrepo.com/show/13539/market-analysis.svg"
    SvgImageFromUrl(
      modifier = Modifier.size(48.dp),
      url = imageUrl,
    )
    SvgImageFromAssetUrl(
      modifier = Modifier.size(48.dp),
      url = "data_analysis.svg",
    )
    SvgImageFromRawRes(
      modifier = Modifier.size(48.dp),
      resId = R.raw.intelligent_positioning,
    )
    SvgImageFromVectorDrawableRes(
      modifier = Modifier.size(48.dp),
      resId = R.drawable.seven24,
    )
  }
}

@Composable
fun SvgImageFromUrl(
  modifier: Modifier = Modifier,
  url: String,
  contentDescription: String? = null,
) {
  AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
      .data(url)
      .decoderFactory(SvgDecoder.Factory())
      .build(),
    contentDescription = contentDescription,
    modifier = modifier,
  )
}

@Composable
fun SvgImageFromAssetUrl(
  modifier: Modifier = Modifier,
  url: String,
  contentDescription: String? = null,
) {
  AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
      .data("file:///android_asset/${url}")
      .decoderFactory(SvgDecoder.Factory())
      .build(),
    contentDescription = contentDescription,
    modifier = modifier,
  )
}

@Composable
fun SvgImageFromRawRes(
  modifier: Modifier = Modifier,
  @RawRes resId: Int,
  contentDescription: String? = null,
) {
  AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
      .data(resId)
      .decoderFactory(SvgDecoder.Factory())
      .build(),
    contentDescription = contentDescription,
    modifier = modifier,
  )
}

@Composable
fun SvgImageFromVectorDrawableRes(
  modifier: Modifier = Modifier,
  @DrawableRes resId: Int,
  contentDescription: String? = null,
) {
  Image(
    painter = painterResource(id = resId),
    contentDescription = contentDescription,
    modifier = modifier,
  )
}

@Preview
@Composable
private fun SvgImageFromUrlPreview() {
  val imageUrl = "https://www.svgrepo.com/show/13539/market-analysis.svg"
  SvgImageFromUrl(
    modifier = Modifier.size(48.dp),
    url = imageUrl,
  )
}

@Preview
@Composable
private fun SvgImageFromAssetUrlPreview() {
  SvgImageFromAssetUrl(
    modifier = Modifier.size(48.dp),
    url = "data_analysis.svg",
  )
}

@Preview
@Composable
private fun SvgImageFromRawResPreview() {
  SvgImageFromRawRes(
    modifier = Modifier.size(48.dp),
    resId = R.raw.intelligent_positioning,
  )
}

@Preview
@Composable
private fun SvgImageFromVectorDrawableResPreview() {
  SvgImageFromVectorDrawableRes(
    modifier = Modifier.size(48.dp),
    resId = R.drawable.seven24,
  )
}