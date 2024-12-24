package dev.vengateshm.compose_material3.ml_kit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mlkit.nl.smartreply.SmartReply
import com.google.mlkit.nl.smartreply.SmartReplySuggestionResult
import com.google.mlkit.nl.smartreply.TextMessage

class SmartRepliesViewModel : ViewModel() {

    private val smartReplyClient = SmartReply.getClient()
    private val conversation = ArrayList<TextMessage>()

    var suggestions by mutableStateOf<List<String>>(emptyList())

    fun generateReply() {
        if (conversation.isEmpty()) return
        smartReplyClient.suggestReplies(
            conversation,
        ).addOnSuccessListener { result ->
            conversation.clear()
            when (result.status) {
                SmartReplySuggestionResult.STATUS_NOT_SUPPORTED_LANGUAGE -> {
                    println("Smart Replies failed : Language not supported")
                }

                SmartReplySuggestionResult.STATUS_NO_REPLY -> {
                    println("Smart Replies failed : No reply")
                }

                SmartReplySuggestionResult.STATUS_SUCCESS -> {
                    result.suggestions.map { it.text }.also {
                        suggestions = it
                    }
                }
            }
        }.addOnFailureListener { e ->
            conversation.clear()
            println("Smart Replies failed : ${e.localizedMessage}")
        }
    }

    fun addRemoteUserText(remoteUserText: String) {
        conversation.add(
            TextMessage.createForRemoteUser(
                remoteUserText,
                System.currentTimeMillis(),
                "remote",
            ),
        )
    }

    fun addLocalUserText(localUserText: String) {
        conversation.add(TextMessage.createForLocalUser(localUserText, System.currentTimeMillis()))
    }
}