@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.feature_delivery.delivery.deliveryui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.reuseable.maps.mapui.MapScreen
import com.example.feature_delivery.delivery.DeliveryData
import com.example.feature_delivery.delivery.nami.DeliveryNamiScreen

@Composable
fun DeliveryMap(navController: NavController) {
    var expandedHandyData by remember { mutableStateOf<DeliveryData?>(null) }
    Scaffold(
        topBar = {
        },
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()){
            // Add the MapScreen Composable
            MapScreen(modifier = Modifier
                .fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(deliveryData) { deliveryData ->
                    DeliveryCard(
                        deliveryData = deliveryData,
                        onClick = {
                            navController.navigate(DeliveryNamiScreen.DeliveryDate.route)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DeliveryMapPreview() {
    DeliveryMap(navController = NavController(LocalContext.current))

}