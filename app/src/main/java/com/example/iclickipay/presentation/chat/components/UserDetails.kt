package com.example.iclickipay.presentation.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iclickipay.R
import com.example.iclickipay.presentation.chat.domain.ChatListDataObject
import com.example.iclickipay.presentation.chat.domain.Message
import com.example.iclickipay.presentation.chat.domain.MessageDeliveryStatus
import com.example.iclickipay.presentation.chat.domain.MessageType

@Composable
fun UserDetails(chatData: ChatListDataObject){
    Column (
        modifier = Modifier.wrapContentHeight()
            .padding(start = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ){
        MessageHeader(chatData)
        MessageSubSection(chatData)
    }
}

@Composable
fun MessageHeader(chatData: ChatListDataObject) {
    Row{
        TextComponent(value = chatData.userName)
    }
}

@Composable
fun MessageSubSection(chatData: ChatListDataObject) {

}

@Preview(showBackground = true)
@Composable
fun UserDetailsPreview(){
    UserDetails(chatData = ChatListDataObject(
        chatId = 4,
        message = Message(
            content = "Instant delivery !",
            timeStamp = "02/03/2019",
            type = MessageType.TEXT,
            deliveryStatus = MessageDeliveryStatus.PENDING
        ),
        userName = "Burger delivery",
        userImage = R.drawable.burger_delivery) )
}

@Preview(showBackground = true)
@Composable
fun MessageHeaderPreview(){
    MessageHeader(chatData = ChatListDataObject(
        chatId = 4,
        message = Message(
            content = "Instant delivery !",
            timeStamp = "02/03/2019",
            type = MessageType.TEXT,
            deliveryStatus = MessageDeliveryStatus.PENDING
        ),
        userName = "Burger delivery",
        userImage = R.drawable.burger_delivery))
}

@Preview(showBackground = true)
@Composable
fun MessageSubSectionPreview(){
    MessageSubSection(chatData = ChatListDataObject(
        chatId = 4,
        message = Message(
            content = "Instant delivery !",
            timeStamp = "02/03/2019",
            type = MessageType.TEXT,
            deliveryStatus = MessageDeliveryStatus.PENDING
        ),
        userName = "Burger delivery",
        userImage = R.drawable.burger_delivery))
}



