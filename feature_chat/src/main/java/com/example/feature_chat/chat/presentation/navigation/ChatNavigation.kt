package com.example.feature_chat.chat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_chat.chat.presentation.screens.ChatHomeScreen

@Composable
fun ChatNavigation (
    onNavigateBack: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ChatScreen.ChatMainScreen.route
    ) {
        composable(route = ChatScreen.ChatMainScreen.route) {
            ChatHomeScreen(
                navController = navController,
                //NAV STEP 2 pass param to screen componant where you want a home button to go back to app home
                onNavigateBack = onNavigateBack
            )
        }
    }

}