package dev.vengateshm.compose_material3.third_party_libraries.og_tags_jsoup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter

@Composable
fun OGTagsScreen(
    modifier: Modifier = Modifier,
    viewModel: OGTagViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    var urlInput by remember {
        mutableStateOf("")
    }
    val ogTagData by viewModel.ogTagData.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Enter URL") },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            value = urlInput,
            onValueChange = {
                urlInput = it
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.getOgTags(urlInput)
            },
        ) {
            Text(text = "Fetch")
        }
        ogTagData?.let {
            OGTagView(ogTagData = it)
        }
    }
}

@Composable
fun OGTagView(
    ogTagData: OgTagData,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            ogTagData.imageUrl?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = "Preview Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = ogTagData.title ?: "No Title",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = ogTagData.description ?: "No Description",
                fontSize = 16.sp,
                color = Color.Gray,
            )
        }
    }
}