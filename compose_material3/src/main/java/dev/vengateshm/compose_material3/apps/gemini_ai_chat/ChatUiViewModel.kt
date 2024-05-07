package dev.vengateshm.compose_material3.apps.gemini_ai_chat

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatUiViewModel : ViewModel() {
    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    fun onEvent(event: ChatUiEvent) {
        when (event) {
            is ChatUiEvent.UpdatePrompt -> {
                _chatState.update {
                    it.copy(prompt = event.newPrompt)
                }
            }

            is ChatUiEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    addPrompt(event.prompt, event.image)
                }

                if (event.image != null) {
                    getResponseWithImage(event.prompt, event.image)
                } else {
                    getResponse(event.prompt)
                }
            }
        }
    }

    private fun addPrompt(prompt: String, image: Bitmap?) {
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, Chat(prompt = prompt, image = image, isFromUser = true))
                },
                prompt = "",
                image = null
            )
        }
    }

    private fun getResponse(prompt: String) {
        viewModelScope.launch {
            val chat = ChatRepo.getResponse(prompt)
            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, chat)
                    },
                    prompt = "",
                    image = null
                )
            }
        }
    }

    private fun getResponseWithImage(prompt: String, image: Bitmap) {
        viewModelScope.launch {
            val chat = ChatRepo.getResponseWithImage(prompt, image)
            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, chat)
                    },
                    prompt = "",
                    image = null
                )
            }
        }
    }
}