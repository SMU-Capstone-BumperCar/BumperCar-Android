package com.example.bumpercar.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bumpercar.data.AuthorData
import com.example.bumpercar.data.ChatMessageData
import com.example.bumpercar.data.ChatMessageWithAuthor
import com.example.bumpercar.data.MessageData
import com.example.bumpercar.data.ReviewData
import com.example.bumpercar.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _chatMessageData = MutableStateFlow(ChatMessageData(emptyList(), AuthorData("","")))
    val chatMessageData = _chatMessageData.asStateFlow()

    private val _chatMessageDataWithAuthor = MutableStateFlow<List<ChatMessageWithAuthor>>(emptyList())
    val chatMessageDataWithAuthor = _chatMessageDataWithAuthor.asStateFlow()

    private val _textField = MutableStateFlow("")
    val textField = _textField.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _reviewData = MutableStateFlow(ReviewData(""))
    val reviewData = _reviewData.asStateFlow()

    fun getTextField(text: String) {
        _textField.update {
            text
        }
    }

    fun sendUserMessage(text: String) {
        // 새로운 메시지 데이터를 작성자와 함께 생성
        val newMessageWithAuthor = ChatMessageWithAuthor(MessageData(text), AuthorData.localUser)

        // 기존 데이터에 새 메시지를 추가
        _chatMessageDataWithAuthor.update { it + newMessageWithAuthor }

        // 기존 _chatMessageData도 업데이트 (필요할 경우)
        _chatMessageData.update {
            it.copy(
                messages = it.messages + MessageData(text),
                interlocutor = AuthorData.localUser
            )
        }

        // API 호출
        getChatbotResponse(text)
    }

    private fun getChatbotResponse(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getChatApi().postDriveJudge(MessageData(query))
                val botMessageWithAuthor = ChatMessageWithAuthor(
                    MessageData(response.body()?.answer?.trimEnd('\n') ?: ""),
                    AuthorData.chatBotAssistant
                )

                // 챗봇 메시지를 리스트에 추가
                _chatMessageDataWithAuthor.update { it + botMessageWithAuthor }

                // 기존 _chatMessageData도 업데이트 (필요할 경우)
                _chatMessageData.update {
                    it.copy(
                        messages = it.messages + MessageData(response.body()?.answer ?: ""),
                        interlocutor = AuthorData.chatBotAssistant
                    )
                }

            } catch (e: Exception) {
                Log.e("ChatViewModel", e.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getReviewResponse(hospitalName: String, onNavigation: () -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getChatApi().postReview(mapOf("query" to hospitalName))
                if (response.isSuccessful) {
                    val answer = response.body()?.answer?.trimEnd('\n') ?: ""
                    Log.d("ChatViewModel", "Received answer: $answer")
                    _reviewData.value = ReviewData(answer)
                    Log.d("ChatViewModel", "Updated reviewData: ${_reviewData.value.answer}")
                    onNavigation() // 성공 시 네비게이션 트리거
                } else {
                    Log.e("ChatViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ChatViewModel", e.toString())
            } finally {
                if (_reviewData.value.answer.isNotEmpty()) {
                    Log.d("ChatViewModel", "Finally")
                    _isLoading.value = false
                }
            }
        }
    }
}