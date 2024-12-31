package com.example.feature_mover.mover



import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController






@SuppressLint("NewApi")
@Composable
fun MoverNavigation(
    onNavigateBack: () -> Unit
) {
    val navController = rememberNavController()
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
            YourStartScreen(navController = navController)

        }
        composable(route = MoverScreenRoutes.YourArrivalScreen.route) {
            YourArrivalScreen(navController = navController)

        }
        composable(route = MoverScreenRoutes.ChooseDateScreen.route) {
            ChooseDate(navController = navController)
        }
        composable(route = MoverScreenRoutes.MoverHomeScreen.route) {
            MoverHomeScreen(navController = navController)
        }
        composable(route = MoverScreenRoutes.MoverProfileScreen.route) {
            MoverProfileScreen(navController = navController)
        }
        composable(route = MoverScreenRoutes.ChooseDateTimeScreen.route) {
            ChooseDateTime(navController = navController)
        }
//        composable(route = MoverScreenRoutes.FilterScreen.route) {
//            FilterScreen(navController = navController)
//        }
//        composable(route = MoverScreenRoutes.SearchScreen.route) {
//            SearchScreen(navController = navController)
//        }
//        composable(route = MoverScreenRoutes.MapScreen.route) {
//            MapScreen(navController = navController)
//        }
        composable(route = MoverScreenRoutes.OrderScreen.route) {
            PlaceOrderScreen(navController = navController)
        }

        
    }
}
