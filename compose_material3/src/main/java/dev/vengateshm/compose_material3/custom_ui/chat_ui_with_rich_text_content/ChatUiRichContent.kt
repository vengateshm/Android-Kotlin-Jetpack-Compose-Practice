package dev.vengateshm.compose_material3.custom_ui.chat_ui_with_rich_text_content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dev.vengateshm.compose_material3.theme.Material3AppTheme

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun ChatUiRichContent(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = viewModel(),
) {
    val messages by viewModel.messages
    val messageText by viewModel.messageText
    val richContent = viewModel.richContent
    val listState = rememberLazyListState()

    Scaffold {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White)
                .contentReceiver {
                    viewModel.handleContent(it)
                },
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(messages) { message ->
                    ChatMessageBubble(
                        message = message,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            if (richContent.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(richContent) { content ->
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.TopEnd,
                        ) {
                            AsyncImage(
                                model = content.uri,
                                contentDescription = "Dropped image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                            )
                            IconButton(
                                modifier = Modifier
                                    .align(Alignment.TopEnd),
                                onClick = { viewModel.removeRichContent(content) },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.RemoveCircle,
                                    contentDescription = "Remove",
                                    tint = Color(0xFF057305),
                                )
                            }
                        }
                    }
                }
            }

            ChatInputField(
                value = messageText,
                onValueChange = viewModel::updateMessageText,
                onSendMessage = { viewModel.sendMessage() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }
}

@Preview("ChatUiRichContent Preview", showBackground = true, showSystemUi = true)
@Composable
fun ChatUiRichContentPreview() {
    Material3AppTheme {
        ChatUiRichContent(modifier = Modifier)
    }
}
