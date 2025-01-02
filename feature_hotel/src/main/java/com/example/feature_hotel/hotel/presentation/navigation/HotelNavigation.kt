package com.example.feature_hotel.hotel.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_hotel.hotel.presentation.screens.ChooseDatesScreen
import com.example.feature_hotel.hotel.presentation.screens.HotelFiltersScreen
import com.example.feature_hotel.hotel.presentation.screens.HotelHomeScreen
import com.example.feature_hotel.hotel.presentation.screens.HotelMaps
import com.example.feature_hotel.hotel.presentation.screens.HotelOrder
import com.example.feature_hotel.hotel.presentation.screens.HotelRooms
import com.example.feature_hotel.hotel.presentation.screens.HotelSearchScreen
import com.example.feature_hotel.hotel.presentation.screens.HotelSingle

@Composable
fun HotelNavigation (
    onNavigateBack: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HotelScreen.HotelMainScreen.route
    ){
        composable(route = HotelScreen.HotelMainScreen.route) {
            HotelHomeScreen(
                navController = navController,
                //NAV STEP 2 pass param to screen componant where you want a home button to go back to app home
                onNavigateBack = onNavigateBack
            )
        }
        composable(route = HotelScreen.ChooseDateScreen.route) {
            ChooseDatesScreen(navController = navController,)
        }
        composable(route = HotelScreen.FilterScreen.route) {
            HotelFiltersScreen(navController = navController,)
        }
        composable(route = HotelScreen.SearchScreen.route) {
            HotelSearchScreen(navController = navController,)
        }
//        composable(route = HotelScreen.HotelMapScreen.route) {
//            HotelMaps(navController = navController,)
//        }
        composable(route= HotelScreen.HotelMapScreen.route){
            HotelMaps(navController = navController)
        }
        composable(route = HotelScreen.OrderScreen.route) {
            HotelOrder(navController = navController,)
        }
        composable(route = HotelScreen.RoomsScreen.route) {
            HotelRooms(navController = navController,)
        }
        composable(route = HotelScreen.SingleScreen.route) {
            HotelSingle(navController = navController,)
        }

    }

}
