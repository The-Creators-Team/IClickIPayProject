package com.example.iclickipay.presentation.chat.data

import com.example.iclickipay.R
import com.example.iclickipay.presentation.chat.domain.ChatListDataObject
import com.example.iclickipay.presentation.chat.domain.Message
import com.example.iclickipay.presentation.chat.domain.MessageDeliveryStatus
import com.example.iclickipay.presentation.chat.domain.MessageType

val chatList = listOf<ChatListDataObject>(
    ChatListDataObject(
        chatId = 1,
        message = Message(
            content = "Hi my love! How are you today?😍",
            timeStamp = "02/03/2019",
            type = MessageType.TEXT,
            deliveryStatus = MessageDeliveryStatus.READ
        ),
        userName = "Rebecca Moore",
        userImage = R.drawable.rebecca),
    ChatListDataObject(
        chatId = 2,
        message = Message(
            content = "Check it out!",
            timeStamp = "02/03/2019",
            type = MessageType.IMAGE,
            deliveryStatus = MessageDeliveryStatus.PENDING
        ),
        userName = "Franz Ferdinand",
        userImage = R.drawable.franz),
    ChatListDataObject(
        chatId = 3,
        message = Message(
            content = "So what???Please tell me why you can't ...",
            timeStamp = "02/03/2019",
            type = MessageType.TEXT,
            deliveryStatus = MessageDeliveryStatus.DELIVERED
        ),
        userName = "My buddyz",
        userImage = R.drawable.buddyz),
    ChatListDataObject(
        chatId = 4,
        message = Message(
            content = "Instant delivery !",
            timeStamp = "02/03/2019",
            type = MessageType.TEXT,
            deliveryStatus = MessageDeliveryStatus.PENDING
        ),
        userName = "Burger delivery",
        userImage = R.drawable.burger_delivery)
)