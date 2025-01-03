package com.example.feature_babysitter.babysitter.navigation

import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.common.reuseable.maps.mapui.MapScreen
import com.example.feature_babysitter.R
import com.example.feature_babysitter.babysitter.data.BabySitterViewModel
import com.example.feature_babysitter.babysitter.data.Babysitter
import com.example.feature_babysitter.babysitter.data.Child
import com.example.feature_babysitter.babysitter.view.BabyMapScreen
import com.example.feature_babysitter.babysitter.view.YourChildDetailsScreen
import com.example.feature_babysitter.babysitter.view.BabySitterMainScreen
import com.example.feature_babysitter.babysitter.view.ChildListScreen
import com.example.feature_babysitter.babysitter.view.EditChildDetailsScreen
import com.example.feature_babysitter.babysitter.view.FilterScreen
import com.example.feature_babysitter.babysitter.view.OrderScreen
import com.example.feature_babysitter.babysitter.view.SearchScreen
import com.example.feature_babysitter.babysitter.view.TakeAPhotoScreen

@Composable
fun BabySitterNavigation(
    onNavigateBack: () -> Unit
) {
    val viewModel = remember { BabySitterViewModel() }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BabySitterScreen.BabySitterMainScreen.route
    ) {
        composable(route = BabySitterScreen.BabySitterMainScreen.route) {
            BabySitterMainScreen(navController = navController, onNavigateBack = onNavigateBack)
        }
        composable(route = BabySitterScreen.YourChildDetails.route) {
            YourChildDetailsScreen(
                navController = navController,
                viewModel = viewModel,
                onNavigateBack = onNavigateBack
            )
        }
        composable(
            route = BabySitterScreen.EditChildDetails.route + "/{index}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            EditChildDetailsScreen(
                navController = navController,
                viewModel = viewModel,
                onNavigateBack = onNavigateBack,
                index = entry.arguments?.getString("index")
            )
        }
        composable(route = BabySitterScreen.TakeAPhotoScreen.route) {
            TakeAPhotoScreen(navController = navController)
        }
        composable(route = BabySitterScreen.ChildListScreen.route) {
            ChildListScreen(
                navController = navController,
                viewModel = viewModel,
                onNavigateBack = onNavigateBack
            )
        }
        composable(route = BabySitterScreen.FilterScreen.route) {
            FilterScreen(navController = navController, viewModel)
        }
        composable(route = BabySitterScreen.SearchScreen.route + "/{sort}/{max}/{min}",
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
        composable(route = BabySitterScreen.BabyMapScreen.route + "/{sort}/{max}/{min}",
            arguments = listOf(
                navArgument("sort") { type = NavType.StringType },
                navArgument("max") { type = NavType.StringType },
                navArgument("min") { type = NavType.StringType }
            )
        ) { entry ->
            BabyMapScreen(
                navController = navController,
                viewModel = viewModel,
                sort = entry.arguments?.getString("sort"),
                max = entry.arguments?.getString("max"),
                min = entry.arguments?.getString("min")
            )
        }
        composable(route = BabySitterScreen.OrderScreen.route + "/{indexBabySitter}/{indexChild}",
            arguments = listOf(
                navArgument("indexBabySitter") { type = NavType.StringType },
                navArgument("indexChild") { type = NavType.StringType }
            )
        ) { entry ->
            OrderScreen(
                navController = navController,
                viewModel = viewModel,
                indexBabySitter = entry.arguments?.getString("indexBabySitter"),
                indexChild = entry.arguments?.getString("indexChild")
            )
        }
    }
}



