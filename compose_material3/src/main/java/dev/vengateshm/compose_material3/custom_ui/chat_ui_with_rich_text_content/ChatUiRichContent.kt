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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dev.vengateshm.compose_material3.theme.Material3AppTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun DateHeader(timestamp: Long) {
    Text(
        text = formatDateHeader(timestamp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        color = Color.Gray,
        fontWeight = FontWeight.Medium,
    )
}

fun formatDateHeader(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    val messageDate = Date(timestamp)

    // Reset time to midnight for comparison
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    val todayMidnight = calendar.timeInMillis

    calendar.timeInMillis = timestamp
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    val messageMidnight = calendar.timeInMillis

    val daysDiff = ((todayMidnight - messageMidnight) / (24 * 60 * 60 * 1000)).toInt()

    return when (daysDiff) {
        0 -> "Today"
        1 -> "Yesterday"
        in 2..6 -> SimpleDateFormat("EEEE", Locale.getDefault()).format(messageDate)
        else -> SimpleDateFormat("EEE, d MMM", Locale.getDefault()).format(messageDate)
    }
}

fun shouldShowDateHeader(currentMessage: ChatMessage, previousMessage: ChatMessage?): Boolean {
    if (previousMessage == null) return true

    val currentCalendar = Calendar.getInstance().apply { timeInMillis = currentMessage.timestamp }
    val previousCalendar = Calendar.getInstance().apply { timeInMillis = previousMessage.timestamp }

    return currentCalendar.get(Calendar.DAY_OF_YEAR) != previousCalendar.get(Calendar.DAY_OF_YEAR) ||
            currentCalendar.get(Calendar.YEAR) != previousCalendar.get(Calendar.YEAR)
}

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
                items(messages.size) { index ->
                    val message = messages[index]
                    val prevMessage = if (index > 0) messages[index - 1] else null

                    if (shouldShowDateHeader(message, prevMessage)) {
                        DateHeader(timestamp = message.timestamp)
                    }

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
