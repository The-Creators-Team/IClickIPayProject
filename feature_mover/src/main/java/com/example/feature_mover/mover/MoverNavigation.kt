package com.example.feature_mover.mover



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.registerMoverRoutes(navController: NavHostController) {
    composable("MoverScreen") {
        MoverNavigation(navController)
    }
//    composable("MovieScreen") {
//        MoverScreen(navController = navController) // Mover screen composable
//    }
    composable("YourStart") {
        YourStartScreen(navController = navController) // YourStart screen composable
    }
    composable("YourArrival") {
        YourArrivalScreen(navController = navController)
    }
    composable("ChooseDate") {
        ChooseDate(navController = navController)
    }
    composable("ChooseDateTime") {
        ChooseDateTime(navController = navController)
    }
    composable("MoverHomeScreen") {
        MoverHomeScreen(navController = navController)
    }
    composable("MoverListScreen") {
        MoversListScreen(navController = navController)
    }
    composable("MoverProfileScreen") {
        MoverProfileScreen(navController = navController)
    }
    composable("PlaceOrderScreen") {
        PlaceOrderScreen( navController = navController)
    }
}

@Composable
fun MoverNavigation(navController: NavHostController) {
    // Your Feature 1 UI here
   MoverScreen(navController = navController)
}
