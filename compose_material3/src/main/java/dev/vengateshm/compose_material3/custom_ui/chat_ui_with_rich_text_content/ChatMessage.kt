package dev.vengateshm.compose_material3.custom_ui.chat_ui_with_rich_text_content

import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val message: String,
    val timestamp: Long = System.currentTimeMillis(),
    val senderId: String,
    val senderName: String,
    val isCurrentUser: Boolean = false,
)