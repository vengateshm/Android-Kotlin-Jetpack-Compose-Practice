package dev.vengateshm.compose_material3.custom_ui.chat_last_seen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {
  private val currentUserId = "user1"
  private val otherUserId = "user2"
  private var messageIdCounter = 1

  private val _messages = mutableStateListOf<Message>()
  val messages: List<Message> = _messages

  init {
    generateSampleMessages()
  }

  private fun generateSampleMessages() {
    val now = System.currentTimeMillis()
    _messages.addAll(
      listOf(
        Message(
          id = messageIdCounter++,
          text = "Hey!",
          senderId = otherUserId,
          timestamp = now - 50000,
        ),
        Message(
          id = messageIdCounter++,
          text = "How are you?",
          senderId = otherUserId,
          timestamp = now - 40000,
        ),
        Message(
          id = messageIdCounter++,
          text = "I'm good, thanks.",
          senderId = currentUserId,
          timestamp = now - 30000,
          seenAt = now - 20000,
        ),
        Message(
          id = messageIdCounter++,
          text = "Let's meet.",
          senderId = currentUserId,
          timestamp = now - 10000,
          seenAt = now - 5000,
        ),
      ),
    )
  }

  fun sendMessage(text: String) {
    val now = System.currentTimeMillis()
    val newMessage = Message(
      id = messageIdCounter++,
      text = text,
      senderId = currentUserId,
      timestamp = now,
      seenAt = now, // Immediately seen
    )
    _messages.add(newMessage)
  }

  fun getLastSeenMessage(): Message? {
    return _messages
      .filter { it.senderId == currentUserId && it.seenAt != null }
      .maxByOrNull { it.timestamp }
  }
}