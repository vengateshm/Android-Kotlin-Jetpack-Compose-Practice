package dev.vengateshm.compose_material3.api_android.read_external_files.api_35

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dev.vengateshm.compose_material3.utils.requestMediaPermissions

@Composable
fun MediaStoreReaderSample() {
    val context = LocalContext.current
    val activity = LocalContext.current as? Activity
    val mediaStoreReader = remember {
        MediaStoreReader(context)
    }
    val viewModel = viewModel<MediaStoreReaderViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return MediaStoreReaderViewModel(mediaStoreReader) as T
            }
        },
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    activity?.requestMediaPermissions()
                },
            ) {
                Text(text = "Request media permissions")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { viewModel.getFiles() },
            ) {
                Text(text = "Read external media")
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(innerPadding),
            ) {
                items(viewModel.files) {
                    MediaListItem(mediaFile = it)
                }
            }
        }
    }
}

@Composable
fun MediaListItem(mediaFile: MediaFile) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when (mediaFile.type) {
            MediaType.IMAGE -> AsyncImage(
                model = mediaFile.uri,
                contentDescription = null,
                modifier = Modifier.width(100.dp),
            )

            MediaType.VIDEO -> Icon(
                imageVector = Icons.Default.VideoFile,
                contentDescription = null,
                modifier = Modifier.width(100.dp),
            )

            MediaType.AUDIO -> Icon(
                imageVector = Icons.Default.AudioFile,
                contentDescription = null,
                modifier = Modifier.width(100.dp),
            )
        }
        Text(
            text = "${mediaFile.name}-${mediaFile.type}",
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
        )
    }
}