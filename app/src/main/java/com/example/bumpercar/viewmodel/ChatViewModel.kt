package com.example.bumpercar.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bumpercar.data.Author
import com.example.bumpercar.data.ChatMessageData
import com.example.bumpercar.data.Message
import com.example.bumpercar.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {

    private val _message = MutableStateFlow(Message(""))
    val message = _message.asStateFlow()

    private val _chatMessageData = MutableStateFlow(ChatMessageData(emptyList(), Author("","")))
    val chatMessageData = _chatMessageData.asStateFlow()

    private val _textField = MutableStateFlow("")
    val textField = _textField.asStateFlow()

    fun getTextField(text: String) {
        _textField.update {
            text
        }
    }

    fun sendUserMessage(text: String) {
        _chatMessageData.update {
            it.copy(
                messages = it.messages + Message(text),
                interlocutor = Author.localUser
            )
        }
        // API 호출 후 응답을 받아 처리
        getChatbotResponse(text)
    }

    private fun getChatbotResponse(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getChatApi().postDriveJudge(Message(query))
                _chatMessageData.update {
                    it.copy(
                        messages = it.messages + Message(response.body()?.answer ?: ""),
                        interlocutor = Author.chatBotAssistant
                    )
                }
            } catch (e: Exception) {
                Log.e("ChatViewModel", e.toString())
            }
        }
    }
}