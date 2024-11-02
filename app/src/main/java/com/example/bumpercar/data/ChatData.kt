package com.example.bumpercar.data

data class ChatData(
    val messages: List<Message>,
    val interlocutor: Author
) {
    data class Message(
        val text: String,
        val author: Author,
    ) {
        val isFromMe: Boolean
            get() = author.id == Author.MY_ID
    }

    data class Author(
        val id: String,
        val name: String
    ) {
        companion object {
            const val MY_ID = "-1"
            val chatBotAssistant = Author("1", "ChatBotAssistant")
            val localUser = Author(MY_ID, "ME")
        }
    }
}