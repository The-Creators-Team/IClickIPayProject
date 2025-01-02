package com.example.feature_pcrepair.pcrepair

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PcRepairNavigation(
    onNavigateBack: () -> Unit
){
    val navController = rememberNavController()
    val viewModel = remember { PcRepairViewModel() }

    NavHost(
        navController = navController,
        startDestination = PcRepairScreens.PcRepairHomeScreen.route
    ){
        composable(route = PcRepairScreens.PcRepairHomeScreen.route) {
            PcRepairHomeScreen(
                navController = navController,
                onNavigateBack = onNavigateBack
            )
        }
        composable(route = PcRepairScreens.PcRepairProblemScreen.route) {
            PcRepairProblemScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = PcRepairScreens.PcRepairOrderScreen.route) {
            PcRepairOrderScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = PcRepairScreens.PcRepairSearchListScreen.route){
           PcRepairSearchList(
               navController = navController,
               viewModel = viewModel
           )
        }
        composable(route = PcRepairScreens.PcRepairFilterScreen.route){
            PcRepairFilterScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = PcRepairScreens.PcRepairAppointmentPickerScreen.route){
            PcRepairAppointmentPicker(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}