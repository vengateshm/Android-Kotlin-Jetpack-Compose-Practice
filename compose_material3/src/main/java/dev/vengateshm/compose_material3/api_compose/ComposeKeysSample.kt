package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class MessageItem(val id: Int, val message: String)

val initialMessages = listOf(
    MessageItem(1, "Hello"),
    MessageItem(2, "World"),
    MessageItem(3, "How are you?"),
    MessageItem(4, "I'm fine, thank you!"),
    MessageItem(5, "What's up?"),
)

@Composable
fun ComposeKeysSample() {
    var messages by remember {
        mutableStateOf(initialMessages)
    }
    var newMessage by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        /*messages.forEach {
            key(it.id) {
                MessageItemView(messageItem = it)
            }
        }*/
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages, key = { it.id }) {
                MessageItemView(messageItem = it)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = newMessage,
                onValueChange = { newMessage = it },
                placeholder = { Text(text = "Enter your message") },
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (newMessage.isNotBlank()) {
                        // Add last
//                        messages = messages + MessageItem(messages.size + 1, newMessage)
                        // Add first
                        messages = listOf(MessageItem(messages.size + 1, newMessage)) + messages
                        newMessage = ""
                    }
                },
            ) {
                Text(text = "Send")
            }
        }
    }
}

fun getRandomColor() = Color(Random.nextLong(0XFFFFFFFF))

@Composable
fun MessageItemView(messageItem: MessageItem) {
    Text(
        text = messageItem.message,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = getRandomColor())
            .padding(16.dp),
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ComposeKeysSamplePreview() {
    MaterialTheme {
        ComposeKeysSample()
    }
}