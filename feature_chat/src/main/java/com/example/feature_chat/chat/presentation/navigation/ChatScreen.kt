package com.example.feature_chat.chat.presentation.navigation

sealed class ChatScreen(val route: String) {
    object ChatMainScreen : ChatScreen("chat_main_screen")
}