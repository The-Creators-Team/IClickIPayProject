package com.example.feature_delivery.delivery.nami

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_delivery.delivery.deliveryui.DeliveryDate
import com.example.feature_delivery.delivery.deliveryui.DeliveryFilters
import com.example.feature_delivery.delivery.deliveryui.DeliveryHome
import com.example.feature_delivery.delivery.deliveryui.DeliveryMap
import com.example.feature_delivery.delivery.deliveryui.DeliveryProfile
import com.example.feature_delivery.delivery.deliveryui.DeliverySearchScreen
import com.example.feature_delivery.delivery.deliveryui.OrderDetailsPage
import com.example.feature_delivery.delivery.deliveryui.ParcelDetailsPage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DeliveryNavigation(
    onNavigateBack: () -> Unit,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DeliveryNamiScreen.DeliveryHome.route
    ) {
        composable(route = DeliveryNamiScreen.DeliveryHome.route) {
            DeliveryHome(navController = navController, onNavigateBack = onNavigateBack)
        }
        composable(route = DeliveryNamiScreen.OrderDetailsPage.route) {
            OrderDetailsPage(navController = navController,)
        }

        composable(route = DeliveryNamiScreen.DeliverySearch.route) {
            DeliverySearchScreen(
                navController = navController,
            )
        }
        composable(route = DeliveryNamiScreen.DeliveryProfile.route) {
            DeliveryProfile(
                navController = navController,
            )
        }
        composable(route = DeliveryNamiScreen.DeliveryFilters.route) {
            DeliveryFilters(
                navController = navController,
            )
        }
        composable(route = DeliveryNamiScreen.ParcelDetailsPage.route) {
            ParcelDetailsPage(
                navController = navController,
            )
        }
        composable(route = DeliveryNamiScreen.DeliveryMap.route) {
            DeliveryMap(
                navController = navController,
            )
        }
        composable(route = DeliveryNamiScreen.DeliveryDate.route) {
            DeliveryDate(
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