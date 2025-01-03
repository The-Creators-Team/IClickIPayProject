package com.example.feature_mover.presentation.mover.routes


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature_mover.presentation.mover.ChooseDate
import com.example.feature_mover.presentation.mover.ChooseDateTime
import com.example.feature_mover.presentation.mover.MoverHomeScreen
import com.example.feature_mover.presentation.mover.MoverProfileScreen
import com.example.feature_mover.presentation.mover.MoverScreen
import com.example.feature_mover.presentation.mover.PlaceOrderScreen
import com.example.feature_mover.presentation.mover.YourArrivalScreen
import com.example.feature_mover.presentation.mover.YourStartScreen
import com.example.feature_mover.presentation.mover.filter.MoverFilterScreen
import com.example.feature_mover.presentation.mover.viewmodel.MoverViewModel


@SuppressLint("NewApi")
@Composable
fun MoverNavigation(
    onNavigateBack: () -> Unit
) {
    val navController = rememberNavController()
    val moverViewModel: MoverViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = MoverScreenRoutes.MoverInitialScreen.route
    ) {
        composable(route = MoverScreenRoutes.MoverInitialScreen.route) {
            MoverScreen(
                navController = navController,
            )
        }
        composable(route = MoverScreenRoutes.YourStartScreen.route) {
            YourStartScreen(navController = navController, moverViewModel)

        }
        composable(route = MoverScreenRoutes.YourArrivalScreen.route) {
            YourArrivalScreen(navController = navController, moverViewModel)

        }
        composable(route = MoverScreenRoutes.ChooseDateScreen.route) {
            ChooseDate(navController = navController, moverViewModel)
        }
        composable(route = MoverScreenRoutes.MoverHomeScreen.route) {
            MoverHomeScreen(navController = navController, moverViewModel)
        }
        composable(
            route = MoverScreenRoutes.MoverProfileScreen.route + "/{index}",
            arguments = listOf(navArgument("index") { type = NavType.StringType })
        ) { entry ->
            MoverProfileScreen(
                navController = navController,
                moverViewModel,
                entry.arguments?.getString("index")
            )
        }
        composable(route = MoverScreenRoutes.ChooseDateTimeScreen.route) {
            ChooseDateTime(navController = navController, moverViewModel)
        }
        composable(route = MoverScreenRoutes.FilterScreen.route) {
            MoverFilterScreen(
                navController = navController, moverViewModel


            )
        }
//        composable(route = MoverScreenRoutes.SearchScreen.route) {
//            SearchScreen(navController = navController)
//        }
//        composable(route = MoverScreenRoutes.MapScreen.route) {
//            MapScreen(navController = navController)
//        }
        composable(route = MoverScreenRoutes.OrderScreen.route) {
            PlaceOrderScreen(navController = navController, moverViewModel)
        }


    }
}
