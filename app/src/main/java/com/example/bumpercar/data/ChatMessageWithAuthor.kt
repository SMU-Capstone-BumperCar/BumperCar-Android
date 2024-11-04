package com.example.bumpercar.data

data class ChatMessageWithAuthor (
    val messageData: MessageData,
    val authorData: AuthorData,
    var hasAnimated: Boolean = false // 애니메이션 완료 여부
)