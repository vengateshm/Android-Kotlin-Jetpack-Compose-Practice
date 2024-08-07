package dev.vengateshm.compose_material3.apps.gemini_ai_chat

import android.graphics.Bitmap

sealed class ChatUiEvent {
    data class UpdatePrompt(val newPrompt: String) : ChatUiEvent()
    data class SendPrompt(
        val prompt: String,
        val image: Bitmap?
    ) : ChatUiEvent()

    data class SendPromptForStream(
        val prompt: String,
        val image: Bitmap?
    ) : ChatUiEvent()
}