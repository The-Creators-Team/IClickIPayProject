package com.examole.feature_mechanic.presentation.routes


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.examole.feature_mechanic.presentation.MechanicProfileScreen
import com.examole.feature_mechanic.presentation.MechanicsChooseDateTime
import com.examole.feature_mechanic.presentation.MechanicsHomeScreen
import com.examole.feature_mechanic.presentation.MechanicsPlaceOrderScreen
import com.examole.feature_mechanic.presentation.MechanicsStarScreen
import com.examole.feature_mechanic.presentation.YourMechanicFormScreen
import com.examole.feature_mechanic.presentation.viewmodel.MechanicsViewModel


@SuppressLint("NewApi")
@Composable
fun MechanicsNavigation(
    onNavigateBack: () -> Unit
) {
    val navController = rememberNavController()
    val mechanicsViewModel: MechanicsViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = MechanicsScreenRoutes.MechanicsInitialScreen.route
    ) {
        composable(route = MechanicsScreenRoutes.MechanicsInitialScreen.route) {
            MechanicsStarScreen(
                navController = navController,
            )
        }
        composable(route = MechanicsScreenRoutes.YourMechanicFormScreen.route) {
         YourMechanicFormScreen(navController = navController, mechanicsViewModel)

        }
        composable(route = MechanicsScreenRoutes.ChooseDateTimeScreen.route) {
            MechanicsChooseDateTime(navController = navController, mechanicsViewModel)
        }
        composable(route = MechanicsScreenRoutes.MechanicHomeScreen.route) {
            MechanicsHomeScreen(navController = navController, mechanicsViewModel)
        }


        composable(
            route = MechanicsScreenRoutes.MechanicProfileScreen.route + "/{index}",
            arguments = listOf(navArgument("index") { type = NavType.StringType })
        ) {entry ->
            MechanicProfileScreen(navController = navController, mechanicsViewModel,entry.arguments?.getString("index"))
        }

//        composable(route = MechanicsScreenRoutes.FilterScreen.route) {
//            MoverFilter(navController = navController, moverViewModel)
//        }
//        composable(route = MechanicsScreenRoutes.SearchScreen.route) {
//            SearchScreen(navController = navController)
//        }
//        composable(route = MechanicsScreenRoutes.MapScreen.route) {
//            MapScreen(navController = navController)
//        }
        composable(route = MechanicsScreenRoutes.MechanicOrderScreen.route) {
            MechanicsPlaceOrderScreen(navController = navController,mechanicsViewModel)
        }


    }
}
