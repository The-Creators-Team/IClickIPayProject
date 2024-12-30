package com.example.iclickipay.presentation.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.iclickipay.R
import com.example.iclickipay.domain.ChatListDataObject
import com.example.iclickipay.domain.Message
import com.example.iclickipay.domain.MessageDeliveryStatus
import com.example.iclickipay.domain.MessageType

@Composable
fun UserDetails(chatData: ChatListDataObject){
    Column (
        modifier = Modifier.wrapContentHeight().padding()
    ){}
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



