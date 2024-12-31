package com.example.feature_chat.chat.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_chat.chat.data.chatList
import com.example.feature_chat.chat.domain.ChatListDataObject
import com.example.feature_chat.chat.presentation.components.UserDetails
import com.example.feature_chat.chat.presentation.components.UserImage

@Composable
fun ChatsScreen(){
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp).background(Color.LightGray)
    ){

//        item {
//            Spacer(modifier = Modifier.height(10.dp))
//        }

        items(chatList){ chatData ->
            ChatListItem(chatData)
        }
    }
}

@Composable
fun ChatListItem(chatData: ChatListDataObject){
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).background(Color.White)
    ){
        UserImage(chatData.userImage)
        UserDetails(chatData)
    }
}

@Preview(showBackground = true)
@Composable
fun ChatsScreenPreview(){
    ChatsScreen()
}