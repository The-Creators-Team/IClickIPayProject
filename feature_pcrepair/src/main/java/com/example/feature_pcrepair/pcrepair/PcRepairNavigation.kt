package com.example.iclickipay.presentation.pcrepair

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

@Composable
fun PcRepairNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PcRepairScreens.PcRepairHomeScreen.route
    ){
        composable(route = PcRepairScreens.PcRepairHomeScreen.route) {
            PcRepairHomeScreen()
        }
        composable(route = PcRepairScreens.PcRepairProblemScreen.route) {
            PcRepairProblemScreen()
        }
        composable(route = PcRepairScreens.PcRepairOrderScreen.route) {
            PcRepairOrderScreen()
        }
    }
}