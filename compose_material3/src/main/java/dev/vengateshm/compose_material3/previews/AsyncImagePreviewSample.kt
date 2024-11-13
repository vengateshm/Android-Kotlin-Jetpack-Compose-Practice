package dev.vengateshm.compose_material3.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AsyncImagePreviewSample(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SubcomposeAsyncImage(
            model = "https://picsum.photos/seed/picsum/400/400",
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            error = {
                if (LocalInspectionMode.current) {
                    Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
                }
            },
            loading = {
                Icon(imageVector = Icons.Filled.Downloading, contentDescription = null)
            }
        )
        Text(text = "Sample Image")
    }
}