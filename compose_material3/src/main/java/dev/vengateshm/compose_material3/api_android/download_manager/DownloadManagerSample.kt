package dev.vengateshm.compose_material3.api_android.download_manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class ImageData(
    val id: Int,
    val title: String,
    val url: String,
    val isDownloadInProgress: Boolean = false,
    val progress: Float = 0f,
    val remaining: String = "",
    val total: String = ""
) {
    companion object {
        fun getList() = listOf(
            ImageData(
                id = 1,
                title = "Image 1",
                url = "https://sample-videos.com/img/Sample-jpg-image-10mb.jpg"
            ),
            ImageData(
                id = 2,
                title = "Image 2",
                url = "https://sample-videos.com/img/Sample-jpg-image-15mb.jpeg"
            ),
        )
    }
}

@Composable
fun DownloadManagerSample(
    modifier: Modifier = Modifier,
    viewModel: DownloadManagerViewModel
) {

    val imageDataList by viewModel.imageDataList.observeAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(imageDataList!!) { imageData ->
                ImageItem(
                    imageData = imageData,
                    onDownloadClick = {
                        viewModel.downloadImage(imageData)
                    })
            }
        }
    }
}

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    imageData: ImageData,
    onDownloadClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = "https://sample-videos.com/img/Sample-jpg-image-50kb.jpg",
            contentDescription = "Image ${imageData.id}",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(width = 8.dp))
        Column(
            modifier = Modifier
                .weight(weight = 1f)
        ) {
            Text(text = imageData.title)
            Spacer(modifier = Modifier.height(height = 8.dp))
            if (imageData.isDownloadInProgress) {
                LinearProgressIndicator(progress = { imageData.progress / 100.0f })
                Text(text = "${imageData.remaining} / ${imageData.total}")
            }
        }
        IconButton(onClick = onDownloadClick) {
            Icon(imageVector = Icons.Default.Download, contentDescription = "Download icon")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageItemPreview() {
    val imageData = ImageData(id = 1, title = "Image 1", url = "")
    ImageItem(
        imageData = imageData,
        onDownloadClick = {}
    )
}