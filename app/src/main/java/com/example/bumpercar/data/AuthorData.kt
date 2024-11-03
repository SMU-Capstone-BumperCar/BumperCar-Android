package com.example.bumpercar.data

data class AuthorData(
    val id: String,
    val name: String
) {
    companion object {
        private const val MY_ID = "-1"
        private const val CHAT_BOT_ID = "1"
        val chatBotAssistant = AuthorData(CHAT_BOT_ID, "ChatBotAssistant")
        val localUser = AuthorData(MY_ID, "ME")
    }
}