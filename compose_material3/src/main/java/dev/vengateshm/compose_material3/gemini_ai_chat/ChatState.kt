package dev.vengateshm.compose_material3.gemini_ai_chat

import android.graphics.Bitmap

data class ChatState(
    val chatList:MutableList<Chat> = mutableListOf(),
    val prompt :String = "",
    val image: Bitmap? = null
)
