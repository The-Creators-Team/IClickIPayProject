package com.example.feature_chat.chat.data

import com.example.feature_chat.R
import com.example.feature_chat.chat.domain.ChatListDataObject
import com.example.feature_chat.chat.domain.Message
import com.example.feature_chat.chat.domain.MessageDeliveryStatus
import com.example.feature_chat.chat.domain.MessageType

val chatList = listOf<com.example.feature_chat.chat.domain.ChatListDataObject>(
    com.example.feature_chat.chat.domain.ChatListDataObject(
        chatId = 1,
        message = com.example.feature_chat.chat.domain.Message(
            content = "Hi my love! How are you today?😍",
            timeStamp = "02/03/2019",
            type = com.example.feature_chat.chat.domain.MessageType.TEXT,
            deliveryStatus = com.example.feature_chat.chat.domain.MessageDeliveryStatus.READ
        ),
        userName = "Rebecca Moore",
        userImage = R.drawable.rebecca
    ),
    com.example.feature_chat.chat.domain.ChatListDataObject(
        chatId = 2,
        message = com.example.feature_chat.chat.domain.Message(
            content = "Check it out!",
            timeStamp = "02/03/2019",
            type = com.example.feature_chat.chat.domain.MessageType.IMAGE,
            deliveryStatus = com.example.feature_chat.chat.domain.MessageDeliveryStatus.PENDING
        ),
        userName = "Franz Ferdinand",
        userImage = R.drawable.franz
    ),
    com.example.feature_chat.chat.domain.ChatListDataObject(
        chatId = 3,
        message = com.example.feature_chat.chat.domain.Message(
            content = "So what???Please tell me why you can't ...",
            timeStamp = "02/03/2019",
            type = com.example.feature_chat.chat.domain.MessageType.TEXT,
            deliveryStatus = com.example.feature_chat.chat.domain.MessageDeliveryStatus.DELIVERED
        ),
        userName = "My buddyz",
        userImage = R.drawable.buddyz
    ),
    com.example.feature_chat.chat.domain.ChatListDataObject(
        chatId = 4,
        message = com.example.feature_chat.chat.domain.Message(
            content = "Instant delivery !",
            timeStamp = "02/03/2019",
            type = com.example.feature_chat.chat.domain.MessageType.TEXT,
            deliveryStatus = com.example.feature_chat.chat.domain.MessageDeliveryStatus.PENDING
        ),
        userName = "Burger delivery",
        userImage = R.drawable.burger_delivery
    )
)