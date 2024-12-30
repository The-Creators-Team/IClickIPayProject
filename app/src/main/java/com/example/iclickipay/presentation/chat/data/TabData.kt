package com.example.iclickipay.presentation.chat.data

data class TabData(val title: String)

val tabs = listOf(
    TabData(title = Tabs.CHATS.value),
    TabData(title = Tabs.CONTACTS.value),
    TabData(title = Tabs.CALLS.value)
)

enum class Tabs(val value:String){
    CHATS("Chats"),
    CONTACTS("Contacts"),
    CALLS("Calls")
}

const val INITIAL_SCREEN_INDEX = 0
