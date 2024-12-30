package com.example.feature_chat.chat.domain

data class ChatListDataObject(
    val chatId: Int,
    val message: Message,
    val userName: String,
    val userImage: Int

)

data class Message(
    val content: String,
    val deliveryStatus: MessageDeliveryStatus,
    val type: MessageType,
    val timeStamp: String
)

enum class MessageDeliveryStatus{
    DELIVERED,
    READ,
    PENDING,
    ERROR
}

enum class MessageType{
    TEXT,
    AUDIO,
    VIDEO,
    IMAGE
}