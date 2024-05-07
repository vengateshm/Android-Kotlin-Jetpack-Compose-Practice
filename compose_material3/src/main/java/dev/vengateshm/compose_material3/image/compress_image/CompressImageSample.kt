package dev.vengateshm.compose_material3.image.compress_image

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CompressImageSample() {
    val context = LocalContext.current

    var selectedImageUri by remember {
        mutableStateOf(Uri.EMPTY)
    }

    var compressedImage by remember {
        mutableStateOf<Bitmap?>(null)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            galleryLauncher.launch("image/*")
        }) {
            Text(text = "Launch Gallery")
        }

        if (selectedImageUri != Uri.EMPTY) {
            AsyncImage(
                modifier = Modifier.size(250.dp),
                model = selectedImageUri,
                contentDescription = null
            )
            Button(onClick = {
                val drawable = selectedImageUri.toDrawable(context)
                compressedImage = context.reduceImageSize(drawable)
            }) {
                Text(text = "Compress Image")
            }
        }

        if (compressedImage != null) {
            AsyncImage(
                modifier = Modifier.size(250.dp),
                model = compressedImage,
                contentDescription = null
            )
        }
    }
}