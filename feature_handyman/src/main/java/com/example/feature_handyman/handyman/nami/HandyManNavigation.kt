package com.example.feature_handyman.handyman.nami

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_handyman.handyman.data.GenerateDummyHandymen
import com.example.feature_handyman.handyman.ui.HandyManDate
import com.example.feature_handyman.handyman.ui.HandyManFilters
import com.example.feature_handyman.handyman.ui.HandyManHome
import com.example.feature_handyman.handyman.ui.HandyManMap
import com.example.feature_handyman.handyman.ui.HandyManSearchScreen
import com.example.feature_handyman.handyman.ui.HandymanProfile
import com.example.feature_handyman.handyman.ui.HandyPlaceOrderScreen
import com.example.feature_handyman.handyman.ui.YourHandyMan

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HandymanNavigation(
    onNavigateBack: () -> Unit,
) {
    val navController = rememberNavController()

    // States for selected filters
    var selectedOption by remember { mutableStateOf("Sort by") }
    var rating by remember { mutableStateOf(0) }

    // Handyman data
    val handymen = remember { GenerateDummyHandymen() }

    NavHost(
        navController = navController,
        startDestination = HandyManNamiScreen.HandyManHome.route
    ) {
        composable(route = HandyManNamiScreen.HandyManHome.route) {
            HandyManHome(navController = navController, onNavigateBack = onNavigateBack)
        }

        // YourHandyMan Screen
        composable(route = HandyManNamiScreen.YourHandyMan.route) {
            YourHandyMan(navController = navController)
        }

        // Handyman Profile Screen
        composable(route = HandyManNamiScreen.HandyManProfile.route) {
            HandymanProfile(navController = navController)
        }

        // Filters Screen
        composable(route = HandyManNamiScreen.HandyManFilters.route) {
            HandyManFilters(
                navController = navController,
                selectedOption = selectedOption,
                rating = rating,
                onApplyFilters = { newOption, newRating ->
                    selectedOption = newOption
                    rating = newRating
                    navController.popBackStack() // Go back after applying filters
                }
            )
        }

        // Handyman Search Screen with applied filters
        composable(route = HandyManNamiScreen.HandyManSearch.route) {
            // Pass the filters (selectedOption and rating) to SearchScreen
            HandyManSearchScreen(
                navController = navController,
                selectedOption = selectedOption,
                rating = rating,
                handymen = handymen
            )
        }

        // Date Screen

        composable(route = HandyManNamiScreen.HandyManDate.route) {
            HandyManDate(navController = navController, dateViewModel = dateViewModel)
        }

        composable(route = HandyManNamiScreen.HandyPlaceOrderScreen.route) { backStackEntry ->
            // Extract the selectedDate argument
            val selectedDate = backStackEntry.arguments?.getString("selectedDate")
            HandyPlaceOrderScreen(navController = navController, selectedDate = selectedDate ?: "No Date Selected")
        }

        // Map Screen
        composable(route = HandyManNamiScreen.HandyManMap.route) {
            HandyManMap(navController = navController)
        }

    }
}

@Composable
fun MySlider(title: String) {
    var sliderValue by remember { mutableStateOf(0f) }
    Column {
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Value: ${sliderValue.toInt()}")
    }
}