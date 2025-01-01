package com.example.feature_handyman.handyman.nami

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_handyman.R
import com.example.feature_handyman.handyman.ui.HandyManFilters
import com.example.feature_handyman.handyman.ui.HandyManHome
import com.example.feature_handyman.handyman.ui.HandyManMap
import com.example.feature_handyman.handyman.ui.HandymanProfile
import com.example.feature_handyman.handyman.ui.PlaceOrderScreen
import com.example.feature_handyman.handyman.ui.YourHandyMan

@Composable
fun HandymanNavigation(
    onNavigateBack: () -> Unit,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HandyManNamiScreen.HandyManHome.route
    ) {
        composable(route = HandyManNamiScreen.HandyManHome.route) {
            HandyManHome(navController = navController, onNavigateBack = onNavigateBack)
        }
        composable(route = HandyManNamiScreen.YourHandyMan.route) {
            YourHandyMan(navController = navController,)
        }

        composable(route = HandyManNamiScreen.PlaceOrderScreen.route) {
            PlaceOrderScreen(
                navController = navController,
            )
        }
        composable(route = HandyManNamiScreen.HandyManProfile.route) {
            HandymanProfile(
                navController = navController,
            )
        }
        composable(route = HandyManNamiScreen.HandyManFilters.route) {
            HandyManFilters(
                navController = navController,
            )
        }
        composable(route = HandyManNamiScreen.HandyManSearch.route) {
            HandyManFilters(
                navController = navController,
            )
        }
        composable(route = HandyManNamiScreen.HandyManMap.route) {
            HandyManMap(
                navController = navController,
            )
        }
    }
}

@Composable
fun DividerSection(label: String, content: @Composable (() -> Unit)? = null) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                modifier = Modifier.padding(end = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp),
                thickness = 2.dp
            )
        }
        content?.invoke() ?: MySlider(title = "")
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