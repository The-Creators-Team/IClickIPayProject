package com.example.iclickipay.presentation.chat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.iclickipay.data.INITIAL_SCREEN_INDEX
import com.example.iclickipay.data.tabs
import com.example.iclickipay.presentation.chat.components.AppBarComponent
import com.example.iclickipay.presentation.chat.components.TabBarComponent
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(){
    val pagerState = rememberPagerState(pageCount = { tabs.size }, initialPage = INITIAL_SCREEN_INDEX)
    val scope = rememberCoroutineScope()

    Column {
        AppBarComponent()
        TabBarComponent(pagerState = pagerState, initialIndex = INITIAL_SCREEN_INDEX,onTabSelected = {selectedPage ->
            scope.launch {pagerState.animateScrollToPage(selectedPage)}
            })

        HorizontalPager(
            modifier = Modifier.fillMaxSize().background(Color.LightGray),
            state = pagerState
        ) { page ->
            when(page){
                0 -> ChatsScreen()
                1 -> ContactsScreen()
                2 -> CallsScreen()
            }

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}


