package dev.vengateshm.compose_material3.custom_ui.chat_ui_with_rich_text_content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.content.MediaType
import androidx.compose.foundation.content.TransferableContent
import androidx.compose.foundation.content.consume
import androidx.compose.foundation.content.hasMediaType
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {
    private val _messages = mutableStateOf(listOf<ChatMessage>())
    val messages: State<List<ChatMessage>> = _messages

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    var richContent = mutableStateListOf<ChatRichContent>()

    val currentUser = UserDetails("current_user", "You")
    private val otherUser = UserDetails("other_user", "John Doe")

    init {
        loadSampleMessages()
    }

    private fun loadSampleMessages() {
        val now = System.currentTimeMillis()
        val oneDayAgo = now - (24 * 60 * 60 * 1000)
        val twoDaysAgo = now - (2 * 24 * 60 * 60 * 1000)
        val oneWeekAgo = now - (7 * 24 * 60 * 60 * 1000)

        _messages.value = listOf(
            ChatMessage(
                message = "Hey, how was your weekend?",
                senderId = otherUser.id,
                senderName = otherUser.name,
                isCurrentUser = false,
                timestamp = oneWeekAgo,
            ),
            ChatMessage(
                message = "It was great! Went hiking with friends.",
                senderId = currentUser.id,
                senderName = currentUser.name,
                isCurrentUser = true,
                timestamp = oneWeekAgo + 300000,
            ),
            ChatMessage(
                message = "That sounds amazing! I love hiking too.",
                senderId = otherUser.id,
                senderName = otherUser.name,
                isCurrentUser = false,
                timestamp = twoDaysAgo,
            ),
            ChatMessage(
                message = "We should go together sometime!",
                senderId = currentUser.id,
                senderName = currentUser.name,
                isCurrentUser = true,
                timestamp = twoDaysAgo + 600000,
            ),
            ChatMessage(
                message = "Good morning! How are you feeling today?",
                senderId = otherUser.id,
                senderName = otherUser.name,
                isCurrentUser = false,
                timestamp = oneDayAgo,
            ),
            ChatMessage(
                message = "Morning! Feeling great, thanks for asking!",
                senderId = currentUser.id,
                senderName = currentUser.name,
                isCurrentUser = true,
                timestamp = oneDayAgo + 900000,
            ),
            ChatMessage(
                message = "Hello! How are you today?",
                senderId = otherUser.id,
                senderName = otherUser.name,
                isCurrentUser = false,
                timestamp = now - 3600000,
            ),
            ChatMessage(
                message = "I'm doing great, thanks for asking! How about you?",
                senderId = currentUser.id,
                senderName = currentUser.name,
                isCurrentUser = true,
                timestamp = now - 3000000,
            ),
            ChatMessage(
                message = "Wonderful! I have some exciting news to share.",
                senderId = otherUser.id,
                senderName = otherUser.name,
                isCurrentUser = false,
                timestamp = now - 1800000,
            ),
        )
    }

    fun updateMessageText(text: String) {
        _messageText.value = text
    }

    fun sendMessage() {
        if (_messageText.value.isNotBlank()) {
            val newMessage = ChatMessage(
                message = _messageText.value,
                senderId = currentUser.id,
                senderName = currentUser.name,
                isCurrentUser = true,
            )
            _messages.value = _messages.value + newMessage
            _messageText.value = ""
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun handleContent(transferableContent: TransferableContent): TransferableContent? {
        return when {
            transferableContent.hasMediaType(MediaType.Image) -> {
                transferableContent.consume { item ->
                    val uri = item.uri
                    uri?.let { richContent.add(ChatRichContent(it, ChatRichContentType.IMAGE)) }
                    uri != null
                }
            }

            else -> transferableContent
        }
    }

    fun removeRichContent(content: ChatRichContent) {
        richContent.remove(content)
    }
}
