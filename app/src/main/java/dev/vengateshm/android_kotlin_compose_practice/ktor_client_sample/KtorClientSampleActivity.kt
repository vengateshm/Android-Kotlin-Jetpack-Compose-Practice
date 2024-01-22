package dev.vengateshm.android_kotlin_compose_practice.ktor_client_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import java.io.File

class KtorClientSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val viewModel = viewModel<KtorClientViewModel>()
//                UploadImage(viewModel)
                DownloadedImage(viewModel)
            }
        }
    }
}

@Composable
fun UploadImage(viewModel: KtorClientViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AsyncImage(
            modifier = Modifier.aspectRatio(1f),
            model = "file:///android_asset/sample_image.jpg",
            contentDescription = "",
        )
        Button(onClick = {
            val file = File(context.cacheDir, "temp_image.jpg")
            file.createNewFile()
            file.outputStream().use {
                context.assets.open("sample_image.jpg").copyTo(it)
            }
            viewModel.uploadFile(file)
        }) {
            Text(text = "Upload image from resource")
        }
        if (viewModel.isUploading.value) {
            CircularProgressIndicator()
        }
        if (viewModel.transferred.value.isNotEmpty()) {
            Text(text = viewModel.transferred.value)
        }
    }
}

@Composable
fun DownloadedImage(viewModel: KtorClientViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        if (viewModel.downloadedFile.value != null) {
            AsyncImage(
                modifier = Modifier.aspectRatio(1f),
                model = viewModel.downloadedFile.value,
                contentDescription = "",
            )
        }
        Button(onClick = {
            val file = File(context.cacheDir, "temp_image.jpg")
            file.createNewFile()
            viewModel.downloadImage(file)
        }) {
            Text(text = "Upload image from resource")
        }
        if (viewModel.isUploading.value) {
            CircularProgressIndicator()
        }
        if (viewModel.transferred.value.isNotEmpty()) {
            Text(text = viewModel.transferred.value)
        }
    }
}
