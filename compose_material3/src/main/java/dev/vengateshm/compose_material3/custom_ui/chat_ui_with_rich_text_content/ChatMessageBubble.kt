package dev.vengateshm.compose_material3.custom_ui.chat_ui_with_rich_text_content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.theme.InterRegular
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ChatMessageBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier,
) {
    val arrangement = if (message.isCurrentUser) Arrangement.End else Arrangement.Start
    val bubbleColor = if (message.isCurrentUser) Color(0xFFBDDCBD) else Color(0xFFE7E3E6)
    val borderColor = if (message.isCurrentUser) Color(0xFF88A288) else Color(0xFFA6A3A6)
    val textColor = if (message.isCurrentUser) Color(0xFF2B342B) else Color(0xFF2A2626)

    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
    ) {
        Card(
            modifier = Modifier.widthIn(max = 280.dp),
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp,
                bottomStart = if (message.isCurrentUser) 24.dp else 4.dp,
                bottomEnd = if (message.isCurrentUser) 4.dp else 24.dp,
            ),
            border = BorderStroke(width = 1.dp, color = borderColor),
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
            ) {
                if (!message.isCurrentUser) {
                    Text(
                        text = message.senderName,
                        fontSize = 12.sp,
                        fontFamily = InterRegular,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF666666),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Text(
                    text = message.message,
                    fontSize = 16.sp,
                    fontFamily = InterRegular,
                    color = textColor,
                    lineHeight = 20.sp,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatTimestamp(message.timestamp),
                    fontSize = 12.sp,
                    fontFamily = InterRegular,
                    color = Color(0xFF057305),
                    modifier = Modifier.align(
                        if (message.isCurrentUser) Alignment.End else Alignment.Start,
                    ),
                )
            }
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
