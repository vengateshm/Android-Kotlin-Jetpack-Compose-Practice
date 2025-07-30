package dev.vengateshm.compose_material3.custom_ui.chat_last_seen

data class Message(
  val id: Int,
  val text: String,
  val senderId: String,
  val timestamp: Long,
  var seenAt: Long? = null,
)
