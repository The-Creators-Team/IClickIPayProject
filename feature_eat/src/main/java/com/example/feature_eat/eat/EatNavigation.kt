package com.example.feature_eat.eat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_eat.HomeEatApp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EatNavigation(
    onNavigateBack: () -> Unit,
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = EatScreens.WelcomeEatApp.route
    ) {
        composable(route = EatScreens.WelcomeEatApp.route){
            WelcomeEatApp(navController = navController)
        }
        composable(route = EatScreens.HomeEatApp.route){
            HomeEatApp(navController = navController)
        }
        composable(route = EatScreens.StoreDetail.route){
            StoreDetail(navController = navController)
        }
        composable(route = EatScreens.EatOrder.route){
            EatOrderScreen(navController = navController)
        }
    }
}