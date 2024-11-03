package com.example.bumpercar.data

data class ChatMessageData(
    val messages: List<MessageData>,
    val interlocutor: AuthorData
)