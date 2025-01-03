package com.example.feature_housecleaning.housecleaning.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.feature_housecleaning.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.common.reuseable.maps.mapui.MapScreen
import com.example.feature_housecleaning.housecleaning.data.HouseCleaningViewModel
import com.example.feature_housecleaning.housecleaning.data.Cleaner
import com.example.feature_housecleaning.housecleaning.data.House
import com.example.feature_housecleaning.housecleaning.view.FilterScreen
import com.example.feature_housecleaning.housecleaning.view.HouseCalendarScreen
import com.example.feature_housecleaning.housecleaning.view.HouseCleaningMainScreen
import com.example.feature_housecleaning.housecleaning.view.HouseMapScreen
import com.example.feature_housecleaning.housecleaning.view.OrderScreen
import com.example.feature_housecleaning.housecleaning.view.SearchScreen
import com.example.feature_housecleaning.housecleaning.view.YourHouseScreen


@Composable
fun HouseCleaningNavigation(
    //NAV STEP 1 add nav param for mainactivity/homescreen to use
    onNavigateBack: () -> Unit
) {
    val viewModel = remember { HouseCleaningViewModel() }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HouseCleaningScreen.HouseCleaningMainScreen.route
    ) {
        composable(route = HouseCleaningScreen.HouseCleaningMainScreen.route) {
            HouseCleaningMainScreen(
                navController = navController,
                //NAV STEP 2 pass param to screen componant where you want a home button to go back to app home
                onNavigateBack = onNavigateBack
            )
        }
        composable(route = HouseCleaningScreen.YourHouseScreen.route) {
            YourHouseScreen(
                navController = navController,
                viewModel = viewModel,
                onNavigateBack = onNavigateBack
            )
        }
        composable(route = HouseCleaningScreen.HouseCalendarScreen.route) {
            HouseCalendarScreen(navController = navController)
        }
        composable(route = HouseCleaningScreen.FilterScreen.route) {
            FilterScreen(navController = navController, viewModel)
        }
        composable(route = HouseCleaningScreen.SearchScreen.route + "/{sort}/{max}/{min}",
            arguments = listOf(
                navArgument("sort") { type = NavType.StringType },
                navArgument("max") { type = NavType.StringType },
                navArgument("min") { type = NavType.StringType }
            )
        ) { entry ->
            SearchScreen(
                navController = navController,
                viewModel = viewModel,
                sort = entry.arguments?.getString("sort"),
                max = entry.arguments?.getString("max"),
                min = entry.arguments?.getString("min")
            )
        }

        composable(route = HouseCleaningScreen.HouseMapScreen.route + "/{sort}/{max}/{min}",
            arguments = listOf(
                navArgument("sort") { type = NavType.StringType },
                navArgument("max") { type = NavType.StringType },
                navArgument("min") { type = NavType.StringType }
            )
        ) { entry ->
            HouseMapScreen(
                navController = navController,
                viewModel = viewModel,
                sort = entry.arguments?.getString("sort"),
                max = entry.arguments?.getString("max"),
                min = entry.arguments?.getString("min")
            )
        }
        composable(route = HouseCleaningScreen.OrderScreen.route + "/{indexCleaner}",
            arguments = listOf(
                navArgument("indexCleaner") { type = NavType.StringType }
            )
        ) { entry ->
            OrderScreen(
                navController = navController,
                viewModel = viewModel,
                indexCleaner = entry.arguments?.getString("indexCleaner")
            )
        }
    }
}






