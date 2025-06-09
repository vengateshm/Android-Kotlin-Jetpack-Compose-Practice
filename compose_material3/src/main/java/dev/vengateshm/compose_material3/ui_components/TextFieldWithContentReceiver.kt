package dev.vengateshm.compose_material3.ui_components

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.content.MediaType
import androidx.compose.foundation.content.ReceiveContentListener
import androidx.compose.foundation.content.TransferableContent
import androidx.compose.foundation.content.consume
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.content.hasMediaType
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextFieldWithContentReceiver(modifier: Modifier = Modifier) {
    val viewModel = viewModel<TextFieldWithContentReceiverViewModel>()
    val imageList = remember { mutableListOf<Uri>() }

    val receiveContentListener = remember {
        ReceiveContentListener { transferableContent ->
            // Handle the pasted data if it is image data
            when {
                // Check if the pasted data is an image or not
                transferableContent.hasMediaType(MediaType.Image) -> {
                    // Handle for each ClipData.Item object
                    // The consume() method returns a new TransferableContent object containging ignored ClipData.Item objects
                    transferableContent.consume { item ->
                        val uri = item.uri
                        if (uri != null) {
                            imageList.add(uri)
                        }
                        // Mark the ClipData.Item object consumed when the retrieved URI is not null
                        uri != null
                    }
                }
                // Return the given transferableContent when the pasted data is not an image
                else -> transferableContent
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .contentReceiver {
                viewModel.handleContent(it)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        viewModel.selectedImages.takeIf { it.isNotEmpty() }?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.horizontalScroll(rememberScrollState()),
            ) {
                it.forEach {
                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
        BasicTextField(
            state = viewModel.messageState,
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp)
                .border(4.dp, color = Color.Gray)
                .contentReceiver {
                    viewModel.handleContent(it)
                },
//                .contentReceiver(receiveContentListener),
        )
    }
}

class TextFieldWithContentReceiverViewModel : ViewModel() {
    val messageState = TextFieldState()
    var selectedImages = mutableStateListOf<Uri>()

    @OptIn(ExperimentalFoundationApi::class)
    fun handleContent(transferableContent: TransferableContent): TransferableContent? {
//        val newUris = mutableListOf<Uri>()
//        val remaining = content.consume {
//            newUris += it.uri ?: return@consume false
//            true
//        }
//        selectedImages = newUris
//        return remaining
        return when {
            // Check if the pasted data is an image or not
            transferableContent.hasMediaType(MediaType.Image) -> {
                // Handle for each ClipData.Item object
                // The consume() method returns a new TransferableContent object containging ignored ClipData.Item objects
                transferableContent.consume { item ->
                    val uri = item.uri
                    if (uri != null) {
                        selectedImages.add(uri)
                    }
                    // Mark the ClipData.Item object consumed when the retrieved URI is not null
                    uri != null
                }
            }
            // Return the given transferableContent when the pasted data is not an image
            else -> transferableContent
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TextFieldWithContentReceiverPreview() {
    TextFieldWithContentReceiver()
}