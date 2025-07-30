package dev.vengateshm.compose_material3.custom_ui.chat_last_seen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
  val viewModel = viewModel<ChatViewModel>()
  val messages = viewModel.messages
  val lazyListState = rememberLazyListState()
  var newMessageText by remember { mutableStateOf("") }
  val lastSeenMessage = viewModel.getLastSeenMessage()
  Column(modifier = Modifier.fillMaxSize()) {
    LazyColumn(
      modifier = Modifier.weight(1f),
      state = lazyListState,
      contentPadding = PaddingValues(8.dp),
    ) {
      items(messages, key = { it.id }) { message ->
        MessageItem(
          message = message,
          isCurrentUser = message.senderId == "user1",
        )
        if (message == lastSeenMessage) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 12.dp),
            horizontalArrangement = if (message.senderId == "user1") Arrangement.End else Arrangement.Start
          ) {
            Text(
              text = "Seen",
              fontSize = 12.sp,
              color = Color.Gray,
              textAlign = TextAlign.Start
            )
          }
        }
      }
    }
    Row(
      modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
    ) {
      TextField(
        value = newMessageText,
        onValueChange = { newMessageText = it },
        modifier = Modifier.weight(1f),
        placeholder = { Text("Type a message") },
      )
      Spacer(modifier = Modifier.width(8.dp))
      Button(
        enabled = newMessageText.isNotBlank(),
        onClick = {
          if (newMessageText.isNotBlank()) {
            viewModel.sendMessage(newMessageText)
            newMessageText = ""
          }
        },
      ) {
        Text("Send")
      }
    }
  }
}

@Composable
fun MessageItem(message: Message, isCurrentUser: Boolean) {
  val alignment = if (isCurrentUser) Alignment.End else Alignment.Start
  val bubbleColor = if (isCurrentUser) Color(0xFFDCF8C6) else Color.LightGray

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    horizontalAlignment = alignment,
  ) {
    Box(
      modifier = Modifier
        .background(bubbleColor, shape = RoundedCornerShape(8.dp))
        .padding(8.dp),
    ) {
      Text(text = message.text)
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ChatScreenPreview() {
  ChatScreen()
}