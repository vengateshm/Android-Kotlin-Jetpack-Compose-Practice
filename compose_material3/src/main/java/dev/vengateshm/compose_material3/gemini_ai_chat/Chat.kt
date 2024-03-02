package dev.vengateshm.compose_material3.gemini_ai_chat

import android.graphics.Bitmap

data class Chat(
    val prompt: String,
    val image: Bitmap?,
    val isFromUser: Boolean
)
