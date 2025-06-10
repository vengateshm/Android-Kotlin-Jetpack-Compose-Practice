package dev.vengateshm.compose_material3.custom_ui.chat_ui_with_rich_text_content

import android.net.Uri

data class ChatRichContent(
    val uri: Uri,
    val contentType: ChatRichContentType,
)
