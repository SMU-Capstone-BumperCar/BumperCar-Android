package com.example.bumpercar.data

data class ChatData(
    val message: List<Message>,
    val address: Author
) {
    data class Message(
        val text: String,
        val author: Author,
        val icon: Int? = null
    ) {
        val isFromMe: Boolean
            get() = author.id == MY_ID
    }

    data class Author(
        val id: String,
        val name: String
    ) {
        companion object {
            val bot = Author("1", "bot")
            val me = Author(MY_ID, "ME")
        }
    }

    companion object {
        const val MY_ID = "-1"
    }
}