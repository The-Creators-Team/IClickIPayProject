package com.example.feature_laundry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

@Composable
fun LaundryNavigation(
    onNavigateBack: () -> Unit
){
    val navController = rememberNavController()
    val viewModel = remember { LaundryViewModel() }

    NavHost(
        navController = navController,
        startDestination = LaundryScreens.LaundryHomeScreen.route
    ){
        composable(route = LaundryScreens.LaundryHomeScreen.route) {
            LaundryHomeScreen(
                navController = navController,
                onNavigateBack = onNavigateBack
            )
        }
        composable(route = LaundryScreens.LaundryProblemScreen.route) {
            LaundryProblemsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = LaundryScreens.LaundryOrderScreen.route) {
            LaundryOrderScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = LaundryScreens.LaundrySearchListScreen.route){
            LaundrySearchList(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = LaundryScreens.LaundryFilterScreen.route){
            LaundryFilterScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = LaundryScreens.LaundryAppointmentPickerScreen.route){
            LaundryAppointmentPicker(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}