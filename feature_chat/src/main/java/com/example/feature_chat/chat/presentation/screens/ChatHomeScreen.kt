package com.example.feature_chat.chat.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.feature_chat.chat.data.INITIAL_SCREEN_INDEX
import com.example.feature_chat.chat.data.tabs
import com.example.feature_chat.chat.presentation.components.AppBarComponent
import com.example.feature_chat.chat.presentation.components.TabBarComponent
import kotlinx.coroutines.launch

@Composable
fun ChatHomeScreen(navController: NavController,
                   onNavigateBack: () -> Unit){
    val pagerState = rememberPagerState(pageCount = { tabs.size }, initialPage = INITIAL_SCREEN_INDEX)
    val scope = rememberCoroutineScope()

    Column {
        AppBarComponent()
        TabBarComponent(pagerState = pagerState, initialIndex = INITIAL_SCREEN_INDEX,onTabSelected = { selectedPage ->
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
    ChatHomeScreen(navController = NavController(LocalContext.current), onNavigateBack = {})
}


