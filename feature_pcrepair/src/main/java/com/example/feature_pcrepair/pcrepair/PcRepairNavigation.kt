package com.example.feature_pcrepair.pcrepair

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.feature_pcrepair.pcrepair.PcRepairScreens
//import com.example.iclickipay.presentation.pcrepair.PcRepairScreens

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